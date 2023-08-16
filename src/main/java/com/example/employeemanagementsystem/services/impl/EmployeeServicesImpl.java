package com.example.employeemanagementsystem.services.impl;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.dto.EmployeeUpdateDTO;
import com.example.employeemanagementsystem.entities.AttendanceEntity;
import com.example.employeemanagementsystem.entities.Employee;
import com.example.employeemanagementsystem.exceptionhandler.EmployeeNotFoundException;
import com.example.employeemanagementsystem.repositories.AttendanceRepository;
import com.example.employeemanagementsystem.repositories.EmployeeRepository;
import com.example.employeemanagementsystem.services.EmployeeServices;
import com.example.employeemanagementsystem.services.StandardEntityService;
import com.example.employeemanagementsystem.utilities.DateUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServicesImpl implements StandardEntityService, EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    private final Logger logger = LogManager.getLogger(EmployeeServicesImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Scheduled(cron = "0 12,16,20,23 * * 6")
    private void changeActiveStatus() {
        List<Employee> allEmployees = employeeRepository.findAllEmployees();


        for (Employee e : allEmployees) {
            List<AttendanceEntity> byEmployee = attendanceRepository.findByEmployee(e);
            ZonedDateTime now = ZonedDateTime.of(byEmployee.get(byEmployee.size() - 1).getAttendanceDateTime().atStartOfDay(), ZoneId.of("Asia/Kolkata"));
            ZonedDateTime thirtyDaysAgo = ZonedDateTime.now().plusDays(-30);
            if (now.toInstant().isBefore(thirtyDaysAgo.toInstant())){
                e.setActiveStatus("N");
            }
            else{
                logger.info(e.getUniqueEmployeeCode() + " is active ");
            }

        }
    }

    public List<EmployeeDTO> getEmployeeByFirstName(String input) {
        List<EmployeeDTO> responseEmployeeDTO = new ArrayList<>();
        try {
            Optional<List<Employee>> allEmployeesList = Optional.ofNullable(employeeRepository.getEmployeeByFirstNameOrLastNameOrUniqueEmployeeCodeOrEmailIdOrMobileNumber(input, input, input, input, input));
            return responseEmployeeDTO = extractEmployeeListAndMap(allEmployeesList);
        } catch (EmployeeNotFoundException E) {
            logger.error(E.getMessage());
        }
        return null;

    }

    private List<EmployeeDTO> extractEmployeeListAndMap(Optional<List<Employee>> employeeList) throws EmployeeNotFoundException {
        List<EmployeeDTO> responseEmployeeDTO = new ArrayList<>();
        if (employeeList.isPresent()) {
            List<Employee> get = employeeList.get();
            for (int i = 0; i < get.size(); i++) {
                responseEmployeeDTO.add(i, modelMapper.map(get.get(i), EmployeeDTO.class));
            }
            return responseEmployeeDTO;
        } else {
            throw new EmployeeNotFoundException("Exception: Employee Not Found");
        }
    }

    public List<EmployeeDTO> getEmployees() {
        List<EmployeeDTO> responseEmployeeDTO = new ArrayList<>();
        try {
            Optional<List<Employee>> allEmployeesList = Optional.ofNullable(employeeRepository.findAllEmployees());
            return responseEmployeeDTO = extractEmployeeListAndMap(allEmployeesList);
        } catch (EmployeeNotFoundException E) {
            logger.error(E.getMessage());
        }
        return null;
    }

    public EmployeeDTO getEmployee(String employeeUniqueCode) {
        EmployeeDTO responseEmployeeDTO;
        try {
            logger.info(employeeUniqueCode);
            Optional<Employee> employeeByUniqueEmployeeCode = Optional.ofNullable(employeeRepository.getEmployeeByUniqueEmployeeCode(employeeUniqueCode));
            responseEmployeeDTO = modelMapper.map(employeeByUniqueEmployeeCode.orElseThrow(() -> new EmployeeNotFoundException("Not found")), EmployeeDTO.class);
            return responseEmployeeDTO;
        } catch (EmployeeNotFoundException e) {
            logger.error(e.getMessage() + employeeUniqueCode);
        } catch (Exception e) {
            logger.error(e.getMessage() + employeeUniqueCode);
        }
        return null;
    }

    @Override
    public EmployeeUpdateDTO updateEmployee(EmployeeUpdateDTO employee, String uniqueEmployeeCode) {
        EmployeeUpdateDTO responseEmployeeUpdateDTO;
        try {
            Optional<Employee> emp = Optional.ofNullable(employeeRepository.getEmployeeByUniqueEmployeeCode(uniqueEmployeeCode));
            if (emp.isPresent()) {
                Optional<String> alternativeEmailId = Optional.ofNullable(employee.getAlternativeEmailId());
                Optional<String> emailId = Optional.ofNullable(employee.getEmailId());
                String activeStatus = Optional.ofNullable(employee.getActiveStatus()).orElseGet(() -> "Y");
                alternativeEmailId.ifPresent(s -> emp.get().setAlternativeEmailId(s));
                emailId.ifPresent(s -> emp.get().setEmailId(s));
                emp.get().setActiveStatus(activeStatus);
                employee.setActiveStatus(activeStatus);
                employeeRepository.save(emp.get());
                return employee;
            } else {
                throw new EmployeeNotFoundException("Employee not found while updating");
            }

        } catch (EmployeeNotFoundException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        EmployeeDTO responseEmployeeDTO;
        try {
            logger.info("Input -> " + employeeDTO.toString());
            Employee employeeAfterMapping = modelMapper.map(employeeDTO, Employee.class);
            employeeAfterMapping.setActiveStatus("Y");
            employeeAfterMapping.setUniqueEmployeeCode(employeeUniqueCodeGenerator());
            logger.info(employeeAfterMapping.getUniqueEmployeeCode());
            logger.info("Before Saving Final Employee -> " + employeeAfterMapping.toString());
            Optional<Employee> optionalEmployeePostSaving = Optional.of(employeeRepository.save(employeeAfterMapping));
            responseEmployeeDTO = modelMapper.map(optionalEmployeePostSaving.get(), EmployeeDTO.class);
            return responseEmployeeDTO;
        } catch (Exception e) {
            logger.info("In saving Employee, exception -> " + e.getMessage());
            return null;

        }
    }

    private String employeeUniqueCodeGenerator() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String prefixCode = sdf.format(date);
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build();
        return prefixCode + "00000" + randomStringGenerator.generate(6).toUpperCase();
    }


}

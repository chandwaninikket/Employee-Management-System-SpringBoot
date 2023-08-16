package com.example.employeemanagementsystem.services.impl;

import com.example.employeemanagementsystem.dto.AttendanceDTO;
import com.example.employeemanagementsystem.entities.AttendanceEntity;
import com.example.employeemanagementsystem.entities.Employee;
import com.example.employeemanagementsystem.exceptionhandler.EmployeeNotActiveException;
import com.example.employeemanagementsystem.exceptionhandler.EmployeeNotFoundException;
import com.example.employeemanagementsystem.repositories.AttendanceRepository;
import com.example.employeemanagementsystem.repositories.EmployeeRepository;
import com.example.employeemanagementsystem.services.AttendanceServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AttendanceServicesImpl implements AttendanceServices {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    Logger logger = LogManager.getLogger(AttendanceServicesImpl.class);

    @Autowired
    ModelMapper mapper;

    @Override
    public AttendanceDTO addAttendance(String uniqueEmployeeCode) {
        try {
            logger.info("In add attendance");
            AttendanceEntity attendance = new AttendanceEntity();
            Optional<Employee> employee = Optional.ofNullable(employeeRepository.getEmployeeByUniqueEmployeeCode(uniqueEmployeeCode));
            logger.info("In add attendance");
            if(employee.isEmpty()){
                throw new EmployeeNotFoundException("Employee not found");
            }
            else {
                if (employee.get().getActiveStatus().equalsIgnoreCase("Y")) {
                    attendance.setEmployee(employee.get());
                    attendanceRepository.save(attendance);
                    return mapper.map(attendance, AttendanceDTO.class);
                } else {
                    throw new EmployeeNotActiveException("Employee not active");
                }
            }
        } catch (EmployeeNotFoundException | EmployeeNotActiveException E) {
            logger.error("While marking attendance error faced " + E.getMessage());
            return null;
        }
    }

    @Override
    public List<AttendanceDTO> viewAttendance(String uniqueEmployeeCode) {
        try {
            Type listType = new TypeToken<List<AttendanceDTO>>(){}.getType();
            Optional<Employee> employeeEntity = Optional.ofNullable(employeeRepository.getEmployeeByUniqueEmployeeCode(uniqueEmployeeCode));
            if(employeeEntity.isEmpty()) throw new EmployeeNotFoundException("EmployeeNotFound");
            else return mapper.map(attendanceRepository.findByEmployee(employeeEntity.get()), listType);
        } catch (EmployeeNotFoundException E) {
            logger.error("While viewing attendance error faced " + E.getMessage());
            return null;
        }
    }
}

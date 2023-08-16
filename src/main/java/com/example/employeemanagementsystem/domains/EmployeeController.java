package com.example.employeemanagementsystem.domains;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.dto.EmployeeUpdateDTO;
import com.example.employeemanagementsystem.responsehandler.ResponseHandler;
import com.example.employeemanagementsystem.services.impl.EmployeeServicesImpl;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/employee/")
public class EmployeeController {

    private final Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    EmployeeServicesImpl employeeServices;


    @GetMapping("find/all")
    public ResponseEntity<Object> getAllEmployees() {
        List<EmployeeDTO> employee = employeeServices.getEmployees();
        return ResponseHandler.generateResponse("Result", HttpStatus.OK, employee);
    }

    @GetMapping("empId/{uniqueEmployeeCode}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable(name = "uniqueEmployeeCode") String uniqueEmployeeCode) {
        logger.info(uniqueEmployeeCode);
        EmployeeDTO employee = employeeServices.getEmployee(uniqueEmployeeCode);
        return ResponseHandler.generateResponse("Result", HttpStatus.OK, employee);

    }

    @PostMapping("update/{uniqueEmployeeCode}")
    public ResponseEntity<Object> updateEmployee(@PathVariable(name = "uniqueEmployeeCode") String uniqueEmployeeCode , @RequestBody EmployeeUpdateDTO employee){
        logger.info(uniqueEmployeeCode);
        EmployeeUpdateDTO employeeUpdateDTO = employeeServices.updateEmployee(employee, uniqueEmployeeCode);
        return ResponseHandler.generateResponse("Result", HttpStatus.OK, employee);
    }

    @PostMapping("find/byDetails/{uniqueEmployeeCode}")
    public ResponseEntity<Object> getEmployeeByDetails(@PathVariable(name = "uniqueEmployeeCode") String uniqueEmployeeCode){
        logger.info(uniqueEmployeeCode);
        List<EmployeeDTO> employee = employeeServices.getEmployeeByFirstName(uniqueEmployeeCode);
        return ResponseHandler.generateResponse("Result", HttpStatus.OK, employee);

    }

    @PostMapping("save")
    public ResponseEntity<Object> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO responseEmployeeDTO = employeeServices.saveEmployee(employeeDTO);
        return ResponseHandler.generateResponse("Result", HttpStatus.OK, responseEmployeeDTO);
    }


}

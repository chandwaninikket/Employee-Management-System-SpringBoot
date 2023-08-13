package com.example.employeemanagementsystem.domains;

import com.example.employeemanagementsystem.responsehandler.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee/")
public class EmployeeController {

    @GetMapping("get/byempid")
    public ResponseEntity<Object> getEmployeeById(@RequestParam String uniqueEmployeeCode){

        return ResponseHandler.generateResponse("Result", HttpStatus.OK, new Object());

    }


}

package com.example.employeemanagementsystem.exceptionhandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmployeeNotActiveException extends Exception {
    Logger logger = LogManager.getLogger(EmployeeNotFoundException.class);
    public EmployeeNotActiveException(String s){
        super(s);
        logger.error(s);
    }
}
package com.example.employeemanagementsystem.exceptionhandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Exception {
    Logger logger = LogManager.getLogger(EmployeeNotFoundException.class);
    public EmployeeNotFoundException(String s){
        super(s);
        logger.error("Employee Not Found Exception");
    }
}
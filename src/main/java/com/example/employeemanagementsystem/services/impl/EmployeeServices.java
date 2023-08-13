package com.example.employeemanagementsystem.services.impl;

import com.example.employeemanagementsystem.repositories.EmployeeRepository;
import com.example.employeemanagementsystem.services.StandardEntityService;
import org.springframework.beans.factory.annotation.Autowired;


public class EmployeeServices implements StandardEntityService {

    @Autowired
    private EmployeeRepository employeeRepository;




}

package com.example.employeemanagementsystem.services;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.dto.EmployeeUpdateDTO;

import java.util.List;

public interface EmployeeServices {
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    public List<EmployeeDTO> getEmployeeByFirstName(String input);

    public List<EmployeeDTO> getEmployees();

    public EmployeeDTO getEmployee(String employeeUniqueCode);

    public EmployeeUpdateDTO updateEmployee(EmployeeUpdateDTO employee, String uniqueEmployeeCode);

}

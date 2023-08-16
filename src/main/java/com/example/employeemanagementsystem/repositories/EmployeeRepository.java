package com.example.employeemanagementsystem.repositories;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.entities.Employee;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee getEmployeeByUniqueEmployeeCode(String uniqueEmployeeCode);
    @Query(nativeQuery = true,
    name="FindEmployees",
    value= "SELECT * FROM COM_Employeemgmt_employee_mst")
    public List<Employee> findAllEmployees();

    public List<Employee> getEmployeeByFirstNameOrLastNameOrUniqueEmployeeCodeOrEmailIdOrMobileNumber(String firstName, String lastName, String uniqueEmployeeCode, String emailId,
            String mobileNumber);



}

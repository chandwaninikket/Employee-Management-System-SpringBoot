package com.example.employeemanagementsystem.repositories;

import com.example.employeemanagementsystem.entities.AttendanceEntity;
import com.example.employeemanagementsystem.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

    public List<AttendanceEntity> findByEmployee(Employee employee);
}

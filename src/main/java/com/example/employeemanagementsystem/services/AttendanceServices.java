package com.example.employeemanagementsystem.services;

import com.example.employeemanagementsystem.dto.AttendanceDTO;

import java.util.List;

public interface AttendanceServices {

    public AttendanceDTO addAttendance(String uniqueEmployeeCode);

    public List<AttendanceDTO> viewAttendance(String uniqueEmployeeCode);

    //public AttendanceDTO updateAttendance(AttendanceDTO attendanceDTO);
}
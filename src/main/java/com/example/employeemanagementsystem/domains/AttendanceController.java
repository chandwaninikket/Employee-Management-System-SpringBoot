package com.example.employeemanagementsystem.domains;

import com.example.employeemanagementsystem.dto.AttendanceDTO;
import com.example.employeemanagementsystem.responsehandler.ResponseHandler;
import com.example.employeemanagementsystem.services.AttendanceServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AttendanceController {

    Logger logger = LogManager.getLogger(AttendanceController.class);

    @Autowired
    AttendanceServices attendanceServices;

    @PostMapping("/attendance/{uniqueEmployeeCode}/add")
    public ResponseEntity<Object> addAttendance(@PathVariable(name = "uniqueEmployeeCode") String uniqueEmployeeCode, @RequestBody AttendanceDTO attendanceDTO) {
        Optional<AttendanceDTO> attendanceResponseDTO = Optional.ofNullable(attendanceServices.addAttendance(uniqueEmployeeCode));
        return attendanceResponseDTO.map(attendance -> ResponseHandler.generateResponse("Successfully added Person ", HttpStatus.OK, attendance)).orElseGet(() -> ResponseHandler.generateResponse("Unsuccessful", HttpStatus.BAD_REQUEST, "Check request " + uniqueEmployeeCode));
    }

    @GetMapping("/attendance/{uniqueEmployeeCode}/view")
    public ResponseEntity<Object> viewAttendance(@PathVariable(name = "uniqueEmployeeCode") String uniqueEmployeeCode) {
        Optional<List<AttendanceDTO>> attendanceResponseDTO = Optional.ofNullable(attendanceServices.viewAttendance(uniqueEmployeeCode));
        logger.info(attendanceResponseDTO);
        return attendanceResponseDTO.filter(attendance -> attendance.size() > 0).map(attendance -> ResponseHandler.generateResponse("Viewing person ", HttpStatus.OK, attendance.toString())).orElseGet(() -> ResponseHandler.generateResponse("Unsuccessful", HttpStatus.BAD_REQUEST, "Check request " + uniqueEmployeeCode));
    }
}

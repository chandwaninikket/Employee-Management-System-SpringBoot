package com.example.employeemanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttendanceDTO {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long attendanceId;

    @CreatedDate
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String attendanceDateTime;



}
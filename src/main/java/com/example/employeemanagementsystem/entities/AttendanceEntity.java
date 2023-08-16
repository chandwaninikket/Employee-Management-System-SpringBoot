package com.example.employeemanagementsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "COM_EMPLOYEE_ATTENDANCE_MST",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"attendance_date_time", "employee_id"})
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long attendanceId;

    @CreatedDate
    @Temporal(value=TemporalType.DATE)
    @Column(name="attendance_date_time")
    private LocalDate attendanceDateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

}
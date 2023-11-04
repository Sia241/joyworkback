package com.jee.joyworkbackend.entities;

import com.jee.joyworkbackend.security.entities.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor @Data
public class DayWork {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private LocalDate date ;
    private LocalTime startTime ;
    private LocalTime endTime ;
    private Double totalHoursWorked;
    private String totalWorkedFormat;
    private Double overtimeHours;
    private String totalOvertimeWorkedHoursFormat ;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private AppUser appUser;
    @OneToMany(mappedBy = "workDay")
    private Set<Pause> pauses = new HashSet<>() ;

}

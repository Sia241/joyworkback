package com.jee.joyworkbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
public class Pause {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime startTimePause;
    private LocalTime endTimePause;
    private Double duration;
    @ManyToOne
    @JoinColumn(name = "workday_id")
    private DayWork workDay;
}


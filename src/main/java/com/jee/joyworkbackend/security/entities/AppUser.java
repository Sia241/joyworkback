package com.jee.joyworkbackend.security.entities;


import com.jee.joyworkbackend.entities.DayWork;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nom ;
    private String prenom ;
    @Column(unique = true)
    private String email ;
    private String password ;
    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Set<DayWork> daysOfWork = new HashSet<>();
//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    private Set<MoodSubmission> moodSubmissions = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles ;
}

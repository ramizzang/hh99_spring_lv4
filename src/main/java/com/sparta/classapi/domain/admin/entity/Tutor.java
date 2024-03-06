package com.sparta.classapi.domain.admin.entity;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Tutor")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer career;

    @Column(nullable = false)
    private String company;

    @Column(name = "phone_number" , nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy="tutor", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Lecture> lectureList = new ArrayList<>();


    @Builder
    public Tutor(String name, Integer career, String company, String phoneNumber, String description) {
        this.name = name;
        this.career = career;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }
}

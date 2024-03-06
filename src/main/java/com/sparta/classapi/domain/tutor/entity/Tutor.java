package com.sparta.classapi.domain.tutor.entity;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy="tutor", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Lecture> lectureList = new ArrayList<>();
}

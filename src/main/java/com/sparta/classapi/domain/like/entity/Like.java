package com.sparta.classapi.domain.like.entity;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Like {// USER, LECTURE FK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Lecture lecture;

    @Builder
    public Like(User user, Lecture lecture) {
        this.user = user;
        this.lecture = lecture;
    }
}

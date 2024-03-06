package com.sparta.classapi.domain.comment.entity;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name="comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment { //강의댓글, user,lecture FK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    private String comment;
}

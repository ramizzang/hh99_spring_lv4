package com.sparta.classapi.domain.comment.entity;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Column(name = "content")
    private String content;

    @Builder
    public Comment(User user, Lecture lecture, String content) {
        this.user = user;
        this.lecture = lecture;
        this.content = content;
    }

    @Builder
    public Comment(Lecture lecture) {
        this.lecture = lecture;
    }
}

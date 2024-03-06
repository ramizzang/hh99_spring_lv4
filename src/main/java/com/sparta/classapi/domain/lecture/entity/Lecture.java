package com.sparta.classapi.domain.lecture.entity;


import com.sparta.classapi.domain.comment.entity.Comment;
import com.sparta.classapi.domain.tutor.entity.Tutor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="lecture")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LectureCategory category;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt; //등록일

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.PERSIST)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    private Tutor tutor;

    private Integer likes = 0; // 좋아요 저장

}

package com.sparta.classapi.domain.lecture.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.classapi.domain.comment.entity.Comment;
import com.sparta.classapi.domain.admin.entity.Tutor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="lecture")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
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
    @Column(updatable = false)
    private LocalDateTime createdAt; //등록일

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    private Tutor tutor;

    private Integer likes = 0; // 좋아요 저장   강의 번호 + 유저 번호  -> like 테이블에 있으면 좋아요를 눌렀다 - 없으면 안눌렀다 + 1//

    @Builder
    public Lecture(String title, Integer price, String description, LectureCategory category, Tutor tutor) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.tutor = tutor;
    }


    // 좋아요 추가
    public void incrementLikes(){
        this.likes++;
    }

    // 좋아요 감소
    public void decrementLikes() {
        if (this.likes > 0) {
            this.likes--;
        }
    }
}

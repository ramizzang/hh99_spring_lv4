package com.sparta.classapi.domain.like.repository;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.like.entity.Like;
import com.sparta.classapi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
    boolean existsByLectureAndUser(Lecture lecture, User user);
    void deleteByLectureAndUser(Lecture lecture, User user);

}

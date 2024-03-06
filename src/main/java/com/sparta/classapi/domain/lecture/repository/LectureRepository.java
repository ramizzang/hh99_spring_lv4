package com.sparta.classapi.domain.lecture.repository;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.entity.LectureCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture,Long> {
    List<Lecture> findAllByCategory(LectureCategory category);

    List<Lecture> findAllByCategoryOrderByTitleDesc(LectureCategory category);

    List<Lecture> findAllByCategoryOrderByPriceDesc(LectureCategory category);

    List<Lecture> findAllByCategoryOrderByCreatedAtDesc(LectureCategory category);

    List<Lecture> findAllByCategoryOrderByTitleAsc(LectureCategory category);

    List<Lecture> findAllByCategoryOrderByPriceAsc(LectureCategory category);

    List<Lecture> findAllByCategoryOrderByCreatedAtAsc(LectureCategory category);
}

package com.sparta.classapi.domain.comment.repository;

import com.sparta.classapi.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}

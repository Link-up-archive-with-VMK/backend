package com.guide.run.event.entity.repository;

import com.guide.run.event.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCommentRepository extends JpaRepository<Comment,Long> {
}

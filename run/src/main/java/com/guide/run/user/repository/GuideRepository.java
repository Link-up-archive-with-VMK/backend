package com.guide.run.user.repository;

import com.guide.run.user.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideRepository extends JpaRepository<Guide, String> {
}
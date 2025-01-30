package com.cdleldt.course.courseContent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseContentRepository extends JpaRepository<CourseContent, Long> {
  List<CourseContent> findByCourseId(Long courseId);
}

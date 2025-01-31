package com.cdleldt.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
  @Query("SELECT c FROM Course c ORDER BY c.courseCategory")
  List<Course> findAllOrderByCourseCategory();
}
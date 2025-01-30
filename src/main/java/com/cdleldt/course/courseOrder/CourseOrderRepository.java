package com.cdleldt.course.courseOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseOrderRepository extends JpaRepository<CourseOrder, Long> {
  List<CourseOrder> findByCourseIdAndUserId(Long courseId, Long userId);
}
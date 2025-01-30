package com.cdleldt.course.courseOrder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseOrderService {
  private final CourseOrderRepository courseOrderRepository;

  public void checkPayment(Long courseId, Long userId) {
    List<CourseOrder> byCourseIdAndUserId =
      courseOrderRepository.findByCourseIdAndUserId(courseId, userId);
    if (byCourseIdAndUserId.isEmpty()) {
      throw new NotPayedException("NOT_PAYED");
    }
  }
}
package com.cdleldt.course.courseContent;

public class CourseContentNotFoundException extends RuntimeException {
  public CourseContentNotFoundException(String message) {
    super(message);
  }
}
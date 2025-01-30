package com.cdleldt.course.courseOrder;

public class NotPayedException extends RuntimeException {
  public NotPayedException(String message) {
    super(message);
  }
}

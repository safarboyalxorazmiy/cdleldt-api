package com.cdleldt.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponseDTO {
  private Long id;
  private String courseCategory;
  private String title;
  private String title2;
  private String description;
  private String description2;
  private String description3;
  private String description4;
  private Integer price;
}

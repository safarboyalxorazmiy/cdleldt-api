package com.cdleldt.course.courseContent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseContentDTO {
  private Long id;
  private String title;
  private String videoUrl;
  private String pdfUrl;
}

package com.cdleldt.course.courseContent;

import com.cdleldt.course.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "course_content")
public class CourseContent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String videoUrl;

  private String pdfUrl;

  @Column(name = "course_id")
  private Long courseId;

  @ManyToOne
  @JoinColumn(name = "course_id", insertable = false, updatable = false)
  private Course course;
}

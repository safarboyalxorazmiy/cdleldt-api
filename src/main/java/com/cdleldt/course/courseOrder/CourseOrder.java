package com.cdleldt.course.courseOrder;

import com.cdleldt.course.Course;
import com.cdleldt.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "course_order")
public class CourseOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private User user;

  @Column(name = "course_id")
  private Long courseId;

  @ManyToOne
  @JoinColumn(name = "course_id", insertable = false, updatable = false)
  private Course course;
}

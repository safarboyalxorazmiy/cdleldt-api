package com.cdleldt.course;

import com.cdleldt.course.courseContent.CourseContentDTO;
import com.cdleldt.course.courseContent.CourseContentService;
import com.cdleldt.course.courseContent.CourseThemesDTO;
import com.cdleldt.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
  private final CourseContentService courseContentService;
  private final CourseService courseService;

  @GetMapping("/themes/{courseId}")
  public ResponseEntity<List<CourseThemesDTO>> getThemes(@PathVariable Long courseId) {
    return ResponseEntity.ok(courseContentService.getThemesByCourseId(courseId, getUser()));
  }

  @GetMapping("/content/by/{id}")
  public ResponseEntity<CourseContentDTO> getCourseContentById(@PathVariable Long id) {
    return ResponseEntity.ok(courseContentService.getCourseContentById(id, getUser()));
  }

  @GetMapping("/list")
  public ResponseEntity<List<CourseResponseDTO>> getCourseList() {
    return ResponseEntity.ok(courseService.getCourseResponse());
  }


  private User getUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (User) authentication.getPrincipal();
  }

}

package com.cdleldt.course.courseContent;

import com.cdleldt.course.courseOrder.CourseOrderService;
import com.cdleldt.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseContentService {
  private final CourseContentRepository courseContentRepository;
  private final CourseOrderService courseOrderService;

  public List<CourseThemesDTO> getThemesByCourseId(Long courseId, User user) {
    courseOrderService.checkPayment(courseId, user.getId());

    List<CourseContent> byCourseId = courseContentRepository.findByCourseId(courseId);

    List<CourseThemesDTO> response = new ArrayList<>();
    for (CourseContent courseContent : byCourseId) {
      CourseThemesDTO themesDTO = new CourseThemesDTO();
      themesDTO.setTheme(courseContent.getTitle());
      themesDTO.setId(courseContent.getId());
      response.add(themesDTO);
    }

    return response;
  }

  public CourseContentDTO getCourseContentById(Long id, User user) {
    Optional<CourseContent> byId = courseContentRepository.findById(id);
    if (byId.isEmpty()) {
      throw new CourseContentNotFoundException("NO_COURSE");
    }

    CourseContent courseContent = byId.get();
    courseOrderService.checkPayment(courseContent.getCourseId(), user.getId());

    CourseContentDTO courseContentDTO = new CourseContentDTO();
    courseContentDTO.setId(courseContent.getId());
    courseContentDTO.setTitle(courseContent.getTitle());
    courseContentDTO.setPdfUrl(courseContent.getPdfUrl());
    courseContentDTO.setVideoUrl(courseContent.getVideoUrl());
    return courseContentDTO;
  }
}
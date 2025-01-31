package com.cdleldt.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
  private final CourseRepository courseRepository;

  public List<CourseResponseDTO> getCourseResponse() {
    List<Course> all = courseRepository.findAllOrderByCourseCategory();
    List<CourseResponseDTO> response = new ArrayList<>();
    for (Course course : all) {
      CourseResponseDTO responseDTO = new CourseResponseDTO();
      responseDTO.setId(course.getId());
      responseDTO.setCourseCategory(course.getCourseCategory());
      responseDTO.setTitle(course.getTitle());
      responseDTO.setTitle2(course.getTitle2());
      responseDTO.setDescription(course.getDescription());
      responseDTO.setDescription2(course.getDescription2());
      responseDTO.setDescription3(course.getDescription3());
      responseDTO.setDescription4(course.getDescription4());
      responseDTO.setPrice(course.getPrice());
      response.add(responseDTO);
    }

    return response;
  }
}
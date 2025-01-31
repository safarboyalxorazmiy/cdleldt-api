package com.cdleldt;

import com.cdleldt.course.Course;
import com.cdleldt.course.CourseRepository;
import com.cdleldt.course.courseContent.CourseContent;
import com.cdleldt.email.EmailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class SecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecurityApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(CourseRepository courseRepository) {
    return args -> {
      Course course = new Course();
      course.setCourseCategory("ELDT Courses");
      course.setTitle("ELDT Video MasterCourse™");
      course.setTitle2("More than ELDT theory, MasterCourse sets you up for success at CDL School");
      course.setDescription("30 Videos Taught by Real CDL Instructors");
      course.setDescription2("1,200+ Reviews and Counting");
      course.setDescription3("Arrive confident, reduce anxiety, succeed at CDL school");
      course.setDescription4("Class A, Class B, Class B To A");
      course.setPrice(75);
      course.setDiscountPrice(25);
      courseRepository.save(course);

      course = new Course();
      course.setCourseCategory("ELDT Courses");
      course.setTitle("ELDT Reading™");
      course.setTitle2("Most cost effective ELDT Theory Course");
      course.setDescription("Trusted by 300+ Training Providers");
      course.setDescription2("1,200+ Reviews and Counting");
      course.setDescription3("Written by Seasoned CDL Instructors");
      course.setDescription4("Class A, Class B, Class B To A");
      course.setPrice(50);
      course.setDiscountPrice(50);
      courseRepository.save(course);

      course = new Course();
      course.setCourseCategory("Hazmat Endorsement Courses");
      course.setTitle("Hazmat Endorsement Video MasterCourse™");
      course.setTitle2("FMCSA Compliant ELDT Hazmat Endorsement");
      course.setDescription("All-in-One Hazmat Endorsement Course");
      course.setDescription2("Certification After Completion");
      course.setDescription3("Designed by Seasoned CDL Instructors");
      course.setDescription4("");
      course.setPrice(55);
      course.setDiscountPrice(25);
      courseRepository.save(course);

      course = new Course();
      course.setCourseCategory("Hazmat Endorsement Courses");
      course.setTitle("Tanker Endorsement (N)");
      course.setTitle2("Tanker Endorsement Online Training");
      course.setDescription("All-in-One Hazmat Endorsement Course");
      course.setDescription2("Certification After Completion");
      course.setDescription3("Designed by Seasoned CDL Instructors");
      course.setDescription4("");
      course.setPrice(25);
      course.setDiscountPrice(25);
      courseRepository.save(course);

      course = new Course();
      course.setCourseCategory("School Bus Courses");
      course.setTitle("School Bus (S)");
      course.setTitle2("FMCSA Compliant ELDT School Bus Endorsement");
      course.setDescription("All-in-One School Bus Endorsement Course");
      course.setDescription2("Certification After Completion");
      course.setDescription3("Designed by Seasoned CDL Instructors");
      course.setDescription4("");
      course.setPrice(55);
      course.setDiscountPrice(25);
      courseRepository.save(course);

      course = new Course();
      course.setCourseCategory("School Bus Courses");
      course.setTitle("Passenger (P)");
      course.setTitle2("Most cost effective ELDT Theory Course");
      course.setDescription("All-in-One Passenger Endorsement Course");
      course.setDescription2("Certification After Completion");
      course.setDescription3("Designed by Seasoned CDL Instructors");
      course.setDescription4("");
      course.setPrice(25);
      course.setDiscountPrice(25);
      courseRepository.save(course);
    };
  }
}
package com.web.desenvolvimento.edusphere.repositories;


import com.web.desenvolvimento.edusphere.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, Long> {
}

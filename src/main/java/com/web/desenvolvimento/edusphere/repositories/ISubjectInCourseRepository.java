package com.web.desenvolvimento.edusphere.repositories;

import com.web.desenvolvimento.edusphere.domain.subjectInCourse.SubjectInCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectInCourseRepository extends JpaRepository<SubjectInCourse, Long> {
}

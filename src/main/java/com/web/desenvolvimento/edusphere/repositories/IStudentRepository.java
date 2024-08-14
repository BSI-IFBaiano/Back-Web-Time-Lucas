package com.web.desenvolvimento.edusphere.repositories;

import com.web.desenvolvimento.edusphere.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, Long> {
}

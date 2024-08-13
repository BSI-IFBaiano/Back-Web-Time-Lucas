package com.web.desenvolvimento.edusphere.repositories;

import com.web.desenvolvimento.edusphere.domain.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
}

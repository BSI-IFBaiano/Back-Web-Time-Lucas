package com.web.desenvolvimento.edusphere.repositories;

import com.web.desenvolvimento.edusphere.domain.studentRegistration.StudentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRegistrationRepository extends JpaRepository<StudentRegistration, Long> {
}

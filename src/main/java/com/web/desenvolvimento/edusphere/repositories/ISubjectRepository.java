package com.web.desenvolvimento.edusphere.repositories;

import com.web.desenvolvimento.edusphere.domain.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubjectRepository extends JpaRepository<Subject, Long> {
}

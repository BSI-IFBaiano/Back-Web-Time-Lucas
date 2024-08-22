package com.web.desenvolvimento.edusphere.domain.subjectInCourse.exceptions;

public class CourseOrSubjectNotFoundException extends RuntimeException {
    public CourseOrSubjectNotFoundException(String message) {
        super(message);
    }
}

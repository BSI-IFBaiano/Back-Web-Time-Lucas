package com.web.desenvolvimento.edusphere.domain.course.exceptions;

public class CourseOrUserNotFoundException extends RuntimeException {
    public CourseOrUserNotFoundException(String message) {
        super(message);
    }
}

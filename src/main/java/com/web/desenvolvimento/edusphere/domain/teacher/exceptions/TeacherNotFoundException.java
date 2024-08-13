package com.web.desenvolvimento.edusphere.domain.teacher.exceptions;

public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException(String message) {
        super(message);
    }
}

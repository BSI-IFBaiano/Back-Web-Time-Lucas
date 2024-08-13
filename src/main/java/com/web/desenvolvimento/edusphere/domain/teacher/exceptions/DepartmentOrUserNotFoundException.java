package com.web.desenvolvimento.edusphere.domain.teacher.exceptions;

public class DepartmentOrUserNotFoundException extends RuntimeException{
    public DepartmentOrUserNotFoundException(String message) {
        super(message);
    }
}

package com.web.desenvolvimento.edusphere.domain.manager.expections;

public class ManagerNotFoundException extends RuntimeException {
    public ManagerNotFoundException(String message) {
        super(message);
    }
}

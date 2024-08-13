package com.web.desenvolvimento.edusphere.domain.user.exceptions;

public class UserNotHaveThisRoleException extends RuntimeException{
    public UserNotHaveThisRoleException(String message) {
        super(message);
    }
}

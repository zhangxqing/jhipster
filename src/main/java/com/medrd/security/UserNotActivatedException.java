package com.medrd.security;

import org.springframework.security.core.AuthenticationException;

/**
 * 如果未激活的用户试图进行身份验证，则抛出此异常。
 */
public class UserNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }
}

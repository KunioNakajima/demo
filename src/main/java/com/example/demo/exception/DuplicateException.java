package com.example.demo.exception;

import lombok.Getter;

@Getter
public class DuplicateException extends RuntimeException {

    private final String username;
    private final String redirect;

    public DuplicateException(String username, String redirect) {
        this.username = username;
        this.redirect = redirect;
    }
}

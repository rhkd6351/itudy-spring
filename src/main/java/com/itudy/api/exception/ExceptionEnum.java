package com.itudy.api.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "B0001"),
    RUNTIME_EXCEPTION_DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "B0002", "이미 다른 소셜 로그인을 통해 가입된 이메일 입니다."),
    DUPLICATED_EXCEPTION(HttpStatus.BAD_REQUEST, "B0003", "중복된 정보입니다."),
    RUNTIME_EXCEPTION_NOT_ACTIVATED(HttpStatus.BAD_REQUEST, "B0005", "활성화 되지 않았습니다."),
    NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "N0001"),

    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "A0001"),
    EXPIRED_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "A0002", "invalid token"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S0001");

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }
}

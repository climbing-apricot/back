package com.apricot.back.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    MEMBER_NOT_FOUND(404, "MEMBER NOT FOUND"),
    PASSWORD_NOT_MATCHED(400, "PASSWORD NOT MATCHED"),
    MEMBER_EMAIL_DUPLICATED(400, "EMAIL DUPLICATED"),
    MEMBER_NICKNAME_DUPLICATED(400, "NICKNAME DUPLICATED"),
    MEMBER_ALREADY_EXISTS(400, "MEMBER ALREADY EXISTS"),
    MEMBER_NOT_AUTHORIZED(403, "MEMBER NOT AUTHORIZED"),;

    private final int code;
    private final String message;
}

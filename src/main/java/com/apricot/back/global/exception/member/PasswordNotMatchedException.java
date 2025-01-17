package com.apricot.back.global.exception.member;

import com.apricot.back.global.exception.CustomException;
import com.apricot.back.global.exception.ErrorCode;

public class PasswordNotMatchedException extends CustomException {
    public PasswordNotMatchedException() {
        super(ErrorCode.PASSWORD_NOT_MATCHED);
    }
}

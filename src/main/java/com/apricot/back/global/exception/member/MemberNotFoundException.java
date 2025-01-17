package com.apricot.back.global.exception.member;

import com.apricot.back.global.exception.CustomException;
import com.apricot.back.global.exception.ErrorCode;

public class MemberNotFoundException extends CustomException {
    public MemberNotFoundException(String message) {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}

package com.apricot.back.global.exception.member;

import com.apricot.back.global.exception.CustomException;
import com.apricot.back.global.exception.ErrorCode;

public class MemberAlreadyExistsException extends CustomException {
    public MemberAlreadyExistsException(String message) {
        super(ErrorCode.MEMBER_ALREADY_EXISTS);
    }
}

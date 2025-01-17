package com.apricot.back.global.exception.member;

import com.apricot.back.global.exception.CustomException;
import com.apricot.back.global.exception.ErrorCode;

public class MemberNotAuthorizedException extends CustomException {
    public MemberNotAuthorizedException() {
        super(ErrorCode.MEMBER_NOT_AUTHORIZED);
    }
}

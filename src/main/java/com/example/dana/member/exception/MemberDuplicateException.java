package com.example.dana.member.exception;

import static com.example.dana.member.constants.MemberErrorConstants.DUPLICATE_MEMBER;

public class MemberDuplicateException extends RuntimeException {
    public MemberDuplicateException() {
        super(DUPLICATE_MEMBER.getMessage());
    }
}

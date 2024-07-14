package com.example.dana.member.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public enum MemberErrorConstants {
    DUPLICATE_MEMBER("이미 존재하는 회원입니다.");

    private final String message;
}

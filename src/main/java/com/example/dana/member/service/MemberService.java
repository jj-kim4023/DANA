package com.example.dana.member.service;

import com.example.dana.common.security.jwt.controller.response.JwtTokenResponse;
import com.example.dana.member.domain.entity.Member;
import com.example.dana.member.controller.request.LoginRequest;
import com.example.dana.member.controller.request.LogoutRequest;
import com.example.dana.member.controller.request.MemberSignUpRequest;
import org.springframework.transaction.annotation.Transactional;

public interface MemberService {
    @Transactional
    Member signUp(MemberSignUpRequest request);

    JwtTokenResponse login(LoginRequest request);
    void logout(LogoutRequest request);
    Member findByMemberEmail(String email);
}

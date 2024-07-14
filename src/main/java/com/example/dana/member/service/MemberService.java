package com.example.dana.member.service;

import com.example.dana.member.domain.entity.Member;
import com.example.dana.member.dto.JoinRequest;

public interface MemberService {
    Member join(JoinRequest request);
}

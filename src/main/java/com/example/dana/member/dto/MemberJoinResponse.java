package com.example.dana.member.dto;

import com.example.dana.member.constants.RoleType;
import com.example.dana.member.domain.entity.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberJoinResponse {
    private String username;
    private RoleType role;
    public static MemberJoinResponse responseFromMember(Member member) {
        return new MemberJoinResponse(member.getUsername(), member.getRole());
    }
}

package com.example.dana.member.service;

import com.example.dana.member.dto.JoinRequest;
import com.example.dana.member.domain.entity.Member;
import com.example.dana.member.dto.MemberJoinResponse;
import com.example.dana.member.exception.MemberDuplicateException;
import com.example.dana.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member join(JoinRequest request) {
        Boolean isExist = memberRepository.existsByEmail(request.getEmail());
        if (isExist) {
            throw new MemberDuplicateException();
        }
        Member member = Member.generateNormalMember(request, passwordEncoder);
        return memberRepository.save(member);
    }
}

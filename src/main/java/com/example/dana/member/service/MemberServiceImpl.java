package com.example.dana.member.service;

import com.example.dana.common.security.jwt.JwtProvider;
import com.example.dana.member.dto.JoinRequest;
import com.example.dana.member.domain.entity.Member;
import com.example.dana.member.dto.MemberJoinResponse;
import com.example.dana.member.dto.TokenInfo;
import com.example.dana.member.exception.MemberDuplicateException;
import com.example.dana.member.domain.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    public Member join(JoinRequest request) {
        Boolean isExist = memberRepository.existsByEmail(request.getEmail());
        if (isExist) {
            throw new MemberDuplicateException();
        }
        Member member = Member.generateNormalMember(request, passwordEncoder);
        return memberRepository.save(member);
    }

    @Transactional
    public TokenInfo login(String memberId, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        // TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        // return tokenInfo;
        return jwtProvider.generateToken(authentication);
    }
}

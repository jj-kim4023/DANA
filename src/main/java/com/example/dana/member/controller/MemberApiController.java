package com.example.dana.member.controller;

import com.example.dana.member.dto.JoinRequest;
import com.example.dana.member.domain.entity.Member;
import com.example.dana.member.dto.MemberJoinResponse;
import com.example.dana.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> join(@RequestBody @Valid JoinRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.join(request));
    }
}

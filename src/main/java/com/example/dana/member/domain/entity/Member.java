package com.example.dana.member.domain.entity;

import com.example.dana.member.constants.RoleType;
import com.example.dana.member.dto.JoinRequest;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private RoleType role;

    public static Member generateNormalMember(JoinRequest request, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.email = request.getEmail();
        member.username = request.getUsername();
        member.password = passwordEncoder.encode(request.getPassword());
        member.role = RoleType.USER;
        return member;
    }
}

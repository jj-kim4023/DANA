package com.example.dana.member.domain.repository;

import com.example.dana.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsByEmail(String email);
    Member findByUsername(String username);

}

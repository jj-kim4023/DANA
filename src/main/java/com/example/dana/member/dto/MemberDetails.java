package com.example.dana.member.dto;

import com.example.dana.member.domain.entity.Member;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class MemberDetails extends User {
    public MemberDetails(Member account) {
        super(account.getEmail(), account.getPassword(),
                AuthorityUtils.createAuthorityList(account.getRole().toString()));
    }

    public String getEmail() {
        return this.getUsername();
    }
}

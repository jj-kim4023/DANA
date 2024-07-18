package com.example.dana.common.security.jwt.controller.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ReissueTokenResponse extends AbstractTokenResponse {
    private String accessToken;
}

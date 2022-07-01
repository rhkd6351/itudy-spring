package com.itudy.api.domain.user.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * jwt response dto
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class JwtResponse {
    String accessToken;
    String refreshToken;

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

package com.itudy.api.domain.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class UpdateInfo {

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request{
        @NotBlank
        String nickname;
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response{
        String message;
    }

}

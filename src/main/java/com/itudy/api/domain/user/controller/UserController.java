package com.itudy.api.domain.user.controller;

import com.itudy.api.common.dto.ResponseWrapper;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.dto.JwtResponse;
import com.itudy.api.domain.user.service.UserFindService;
import com.itudy.api.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserFindService userFindService;
    private final HttpSession httpSession;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @GetMapping(path = "/login/success")
    public ResponseEntity<ResponseWrapper<JwtResponse>> loginSuccess(){

        Long idx = (Long)httpSession.getAttribute("user");
        UserVO user = userFindService.findByIdx(idx);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), "adminadmin");

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.createToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        ResponseWrapper<JwtResponse> result = new ResponseWrapper<JwtResponse>(new JwtResponse(accessToken, refreshToken), "로그인 성공", HttpStatus.OK.value());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

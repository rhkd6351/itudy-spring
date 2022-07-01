package com.itudy.api.domain.user.controller;

import com.itudy.api.common.dto.ResponseWrapper;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.dto.UpdateInfo;
import com.itudy.api.domain.user.dto.UserDTO;
import com.itudy.api.domain.user.service.UserFindService;
import com.itudy.api.domain.user.service.UserUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserFindService userFindService;
    private final UserUpdateService userUpdateService;

    @GetMapping(path = "/user")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<UserDTO>> getMyInfo() {

        UserVO user = userFindService.getMyUserWithAuthorities();
        UserDTO userDTO = UserDTO.fromEntityDetail(user);

        ResponseWrapper<UserDTO> response = new ResponseWrapper<>(
                userDTO,
                "사용자 정보",
                HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/user")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<String>> updateInfo(
            @RequestBody @Validated UpdateInfo.Request updateInfo) {

        UserVO user = userFindService.getMyUserWithAuthorities();
        userUpdateService.update(user.getIdx(), updateInfo);

        ResponseWrapper<String> response = new ResponseWrapper<>(
                "변경",
                "사용자 닉네임 변경",
                HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

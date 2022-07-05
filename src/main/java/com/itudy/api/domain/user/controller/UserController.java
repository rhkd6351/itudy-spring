package com.itudy.api.domain.user.controller;

import com.itudy.api.domain.common.dto.ResponseWrapper;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.dto.UpdateInfo;
import com.itudy.api.domain.user.dto.UserDTO;
import com.itudy.api.domain.user.service.UserFindService;
import com.itudy.api.domain.user.service.UserUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserFindService userFindService;
    private final UserUpdateService userUpdateService;

    /**
     * get my information
     */
    @GetMapping(path = "/users")
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

    /**
     * update user information (nickname)
     */
    @PutMapping(path = "/users")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<String>> updateUserInfo(
            @RequestBody @Validated UpdateInfo.Request updateInfo) {

        UserVO user = userFindService.getMyUserWithAuthorities();
        userUpdateService.update(user.getIdx(), updateInfo);

        ResponseWrapper<String> response = new ResponseWrapper<>(
                "성공",
                "사용자 닉네임 변경",
                HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * update user image
     */
    @PutMapping(path = "/users/images", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<String>> updateUserImage(
            @RequestPart(value = "image", required = true) MultipartFile image
    ) {

        UserVO user = userFindService.getMyUserWithAuthorities();
        userUpdateService.updateImage(user.getIdx(), image);

        ResponseWrapper<String> response = new ResponseWrapper<>(
                "이미지 변경 성공",
                "사용자 이미지 변경",
                HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

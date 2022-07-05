package com.itudy.api.domain.user.service;


import com.itudy.api.domain.common.entity.CommonImageVO;
import com.itudy.api.domain.common.service.CommonImageService;
import com.itudy.api.domain.user.domain.AuthVO;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.dto.UpdateInfo;
import com.itudy.api.domain.user.repository.UserRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserRepository userRepository;

    private final AuthFindService authFindService;
    private final UserFindService userFindService;
    private final CommonImageService commonImageService;

    @Value("${server.url}")
    private String host;

    @Transactional
    public Long getSignUp(String email, String oauth, String nickname, String picture) {
        Optional<UserVO> user = userRepository.findByEmail(email);

        if (user.isPresent()){
            if(!oauth.equals(user.get().getOauth()))
                throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION_DUPLICATED_EMAIL);

            return user.get().getIdx();
        }

        AuthVO auth = authFindService.findByName("ROLE_USER");

        UserVO userVO = UserVO.builder()
                .email(email)
                .nickname(nickname)
                .activated(true)
                .oauth(oauth)
                .auth(auth)
                .imageUrl(picture)
                .build();

        return userRepository.save(userVO).getIdx();
    }

    @Transactional
    public void update(Long userIdx, UpdateInfo.Request updateInfo) {
        UserVO user = userFindService.findByIdx(userIdx);
        user.setNickname(updateInfo.getNickname());
    }

    @Transactional
    public void updateImage(Long userIdx, MultipartFile image) {

        UserVO user = userFindService.findByIdx(userIdx);
        CommonImageVO commonImageVO = commonImageService.save(image);
        user.setImageUrl(host + "/api/v1/common/images/" + commonImageVO.getName());

    }
}
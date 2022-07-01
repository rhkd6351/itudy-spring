package com.itudy.api.domain.user.service;


import com.itudy.api.domain.user.domain.AuthVO;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.dto.UpdateInfo;
import com.itudy.api.domain.user.repository.UserRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserRepository userRepository;
    private final AuthFindService authFindService;
    private final UserFindService userFindService;

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
}
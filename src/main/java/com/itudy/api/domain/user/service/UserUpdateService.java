package com.itudy.api.domain.user.service;


import com.itudy.api.domain.user.domain.AuthVO;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserUpdateService {


    private final UserRepository userRepository;
    private final AuthFindService authFindService;

    public Long getSignUp(String email, String oauth, String nickname, String picture) {
        Optional<UserVO> user = userRepository.findByEmail(email);

        if (user.isPresent())
            return user.get().getIdx();

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
}

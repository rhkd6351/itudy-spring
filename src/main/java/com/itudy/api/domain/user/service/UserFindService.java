package com.itudy.api.domain.user.service;


import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.repository.UserRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import com.itudy.api.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserFindService {


    private final UserRepository userRepository;

    public UserVO findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

    public UserVO findByIdx(Long idx) {
        return userRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

    public UserVO getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findByEmail)
                .orElseThrow(
                        () -> new ApiException(ExceptionEnum.RUNTIME_EXCEPTION)
                );
    }


}

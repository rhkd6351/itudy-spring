package com.itudy.api.domain.user.service;


import com.itudy.api.domain.user.domain.AuthVO;
import com.itudy.api.domain.user.repository.AuthRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthFindService {


    private final AuthRepository authRepository;

    public AuthVO findByName(String name){
        return authRepository.findById(name).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }
}

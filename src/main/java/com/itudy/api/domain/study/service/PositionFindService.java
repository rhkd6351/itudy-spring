package com.itudy.api.domain.study.service;

import com.itudy.api.domain.study.domain.PositionVO;
import com.itudy.api.domain.study.repository.PositionRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionFindService {

    private final PositionRepository positionRepository;

    @Transactional(readOnly = true)
    public PositionVO findById(String name){
        return positionRepository.findById(name).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }


    public List<PositionVO> findAll() {
        return positionRepository.findAll();
    }
}

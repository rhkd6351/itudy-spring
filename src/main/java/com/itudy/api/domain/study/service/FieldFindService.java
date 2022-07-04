package com.itudy.api.domain.study.service;

import com.itudy.api.domain.study.domain.FieldVO;
import com.itudy.api.domain.study.repository.FieldRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldFindService {

    private final FieldRepository fieldRepository;

    @Transactional(readOnly = true)
    public Page<FieldVO> getAllByQuery(Pageable pageable, String query){
        return fieldRepository.findByNameContains(pageable, query);
    }

    @Transactional(readOnly = true)
    public FieldVO findByIdx(Long idx){
        return fieldRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

}

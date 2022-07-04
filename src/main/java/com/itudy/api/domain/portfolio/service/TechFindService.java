package com.itudy.api.domain.portfolio.service;

import com.itudy.api.domain.portfolio.entity.TechVO;
import com.itudy.api.domain.portfolio.repository.TechRepository;
import com.itudy.api.domain.study.domain.FieldVO;
import com.itudy.api.domain.study.repository.FieldRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TechFindService {

    private final TechRepository techRepository;

    @Transactional(readOnly = true)
    public Page<TechVO> getAllByQuery(Pageable pageable, String query){
        return techRepository.findByNameContains(pageable, query);
    }

    @Transactional(readOnly = true)
    public TechVO findByIdx(Long idx){
        return techRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

}

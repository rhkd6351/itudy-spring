package com.itudy.api.domain.study.service;

import com.itudy.api.domain.study.domain.FieldVO;
import com.itudy.api.domain.study.domain.StudyVO;
import com.itudy.api.domain.study.repository.FieldRepository;
import com.itudy.api.domain.study.repository.StudyRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudyFindService {

    private final StudyRepository StudyRepository;

    @Transactional(readOnly = true)
    public Page<StudyVO> getAllByQuery(Pageable pageable, String query){
        return StudyRepository.findByNameContains(pageable, query);
    }

    @Transactional(readOnly = true)
    public StudyVO findByIdx(Long idx){
        return StudyRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

}

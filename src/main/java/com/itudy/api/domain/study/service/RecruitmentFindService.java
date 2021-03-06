package com.itudy.api.domain.study.service;

import com.itudy.api.domain.study.domain.RecruitmentVO;
import com.itudy.api.domain.study.domain.StudyVO;
import com.itudy.api.domain.study.repository.RecruitmentRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitmentFindService {

    private final RecruitmentRepository recruitmentRepository;

    @Transactional(readOnly = true)
    public RecruitmentVO findByIdx(Long idx){
        return recruitmentRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

    @Transactional(readOnly = true)
    public Page<RecruitmentVO> getByStudy(StudyVO study, Pageable pageable){
        return recruitmentRepository.findByStudy(study, pageable);
    }

}

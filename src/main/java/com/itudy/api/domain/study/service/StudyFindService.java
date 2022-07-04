package com.itudy.api.domain.study.service;

import com.itudy.api.domain.study.domain.FieldVO;
import com.itudy.api.domain.study.domain.StudyMemberVO;
import com.itudy.api.domain.study.domain.StudyVO;
import com.itudy.api.domain.study.repository.FieldRepository;
import com.itudy.api.domain.study.repository.StudyMemberRepository;
import com.itudy.api.domain.study.repository.StudyRepository;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.service.UserFindService;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyFindService {

    private final StudyRepository StudyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final UserFindService userFindService;

    @Transactional(readOnly = true)
    public Page<StudyVO> getAllByQuery(Pageable pageable, String query){
        return StudyRepository.findByNameContains(pageable, query);
    }

    @Transactional(readOnly = true)
    public Page<StudyVO> getJoinedStudies(Pageable pageable){
        UserVO user = userFindService.getMyUserWithAuthorities();
        Page<StudyMemberVO> mappings = studyMemberRepository.findByUser(user, pageable);

        List<StudyVO> studies = mappings.getContent().stream().map(
                StudyMemberVO::getStudy
        ).toList();

        return new PageImpl(studies, pageable, mappings.getTotalPages());
    }

    @Transactional(readOnly = true)
    public StudyVO findByIdx(Long idx){
        return StudyRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }



}

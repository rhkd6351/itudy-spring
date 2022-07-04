package com.itudy.api.domain.study.service;

import com.itudy.api.domain.study.domain.FieldVO;
import com.itudy.api.domain.study.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldFindService {

    private final FieldRepository fieldRepository;

    @Transactional(readOnly = true)
    public List<FieldVO> getAllByQuery(Pageable pageable, String query){
        return fieldRepository.findByNameContains(pageable, query);
    }

}

package com.itudy.api.domain.community.service;

import com.itudy.api.domain.community.entity.CommunityBoardVO;
import com.itudy.api.domain.community.repository.CommunityBoardRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityBoardFindService {

    private final CommunityBoardRepository communityBoardRepository;

    @Transactional(readOnly = true)
    public CommunityBoardVO findByIdx(Long idx){
        return communityBoardRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }


    @Transactional(readOnly = true)
    public List<CommunityBoardVO> findAll(){
        return communityBoardRepository.findAll();
    }




}

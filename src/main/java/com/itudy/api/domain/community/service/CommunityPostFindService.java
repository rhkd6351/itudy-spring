package com.itudy.api.domain.community.service;

import com.itudy.api.domain.community.entity.CommunityBoardVO;
import com.itudy.api.domain.community.entity.CommunityPostVO;
import com.itudy.api.domain.community.repository.CommunityBoardRepository;
import com.itudy.api.domain.community.repository.CommunityPostRepository;
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
public class CommunityPostFindService {

    private final CommunityPostRepository communityPostRepository;

    @Transactional(readOnly = true)
    public CommunityPostVO findByIdx(Long idx){
        return communityPostRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

    @Transactional(readOnly = true)
    public Page<CommunityPostVO> findByBoard(CommunityBoardVO board, Pageable pageable){
        return communityPostRepository.findByBoard(board, pageable);
    }




}

package com.itudy.api.domain.community.service;

import com.itudy.api.domain.community.entity.CommunityCommentVO;
import com.itudy.api.domain.community.entity.CommunityPostVO;
import com.itudy.api.domain.community.repository.CommunityCommentRepository;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityCommentFindService {

    private final CommunityCommentRepository communityCommentRepository;

    @Transactional(readOnly = true)
    public CommunityCommentVO findByIdx(Long idx){
        return communityCommentRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

    @Transactional(readOnly = true)
    public Page<CommunityCommentVO> findByPost(CommunityPostVO post, Pageable pageable){
        return communityCommentRepository.findByPost(post, pageable);
    }

}

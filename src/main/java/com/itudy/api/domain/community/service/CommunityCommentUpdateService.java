package com.itudy.api.domain.community.service;

import com.itudy.api.domain.community.entity.CommunityCommentVO;
import com.itudy.api.domain.community.entity.CommunityPostVO;
import com.itudy.api.domain.community.repository.CommunityCommentRepository;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.service.UserFindService;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityCommentUpdateService {

    private final CommunityCommentRepository communityCommentRepository;
    private final CommunityPostFindService communityPostFindService;
    private final CommunityCommentFindService communityCommentFindService;
    private final UserFindService userFindService;

    @Transactional
    public Long save(
            String content,
            Long postIdx,
            Long upperCommentIdx
    ){
        UserVO user = userFindService.getMyUserWithAuthorities();
        CommunityPostVO post = communityPostFindService.findByIdx(postIdx);

        CommunityCommentVO upperComment = null;

        if(upperCommentIdx != null){
            upperComment = communityCommentFindService.findByIdx(upperCommentIdx);

            if(upperComment.getUpperComment() != null)
                throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }

        CommunityCommentVO vo = CommunityCommentVO.builder()
                .content(content)
                .post(post)
                .user(user)
                .upperComment(upperComment)
                .build();

        return communityCommentRepository.save(vo).getIdx();
    }


    @Transactional
    public Long save(
            Long idx,
            String content
    ) {
        CommunityCommentVO comment = communityCommentFindService.findByIdx(idx);
        comment.update(content);

        return idx;
    }

}

package com.itudy.api.domain.community.service;

import com.itudy.api.domain.community.entity.CommunityBoardVO;
import com.itudy.api.domain.community.entity.CommunityPostVO;
import com.itudy.api.domain.community.repository.CommunityPostRepository;
import com.itudy.api.domain.user.domain.UserVO;
import com.itudy.api.domain.user.service.UserFindService;
import com.itudy.api.exception.ApiException;
import com.itudy.api.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityPostUpdateService {

    private final CommunityPostRepository communityPostRepository;
    private final CommunityPostFindService communityPostFindService;
    private final CommunityBoardFindService communityBoardFindService;
    private final UserFindService userFindService;

    @Transactional
    public Long save(
            String title,
            String content,
            Long boardIdx
    ){

        UserVO user = userFindService.getMyUserWithAuthorities();
        CommunityBoardVO board = communityBoardFindService.findByIdx(boardIdx);

        CommunityPostVO vo = CommunityPostVO.builder()
                .title(title)
                .content(content)
                .board(board)
                .user(user)
                .build();

        return communityPostRepository.save(vo).getIdx();
    }


    @Transactional
    public Long update(
            Long idx,
            String title,
            String content
    ){
        UserVO user = userFindService.getMyUserWithAuthorities();
        CommunityPostVO post = communityPostFindService.findByIdx(idx);

        if(user != post.getUser())
            throw new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION);

        post.update(title, content);
        return post.getIdx();
    }



}

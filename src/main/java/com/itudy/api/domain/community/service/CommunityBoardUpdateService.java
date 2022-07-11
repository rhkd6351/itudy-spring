package com.itudy.api.domain.community.service;

import com.itudy.api.domain.community.entity.CommunityBoardVO;
import com.itudy.api.domain.community.repository.CommunityBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityBoardUpdateService {

    private final CommunityBoardRepository communityBoardRepository;

    private final CommunityBoardFindService communityBoardFindService;

    @Transactional
    public Long save(String name) {

        CommunityBoardVO vo = CommunityBoardVO.builder()
                .name(name)
                .build();

        return communityBoardRepository.save(vo).getIdx();

    }

    @Transactional
    public Long update(Long idx, String name){
        CommunityBoardVO board = communityBoardFindService.findByIdx(idx);

        board.setName(name);

        return board.getIdx();
    }



}

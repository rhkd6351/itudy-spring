package com.itudy.api.domain.community.dto;

import com.itudy.api.domain.community.entity.CommunityBoardVO;
import com.itudy.api.domain.community.entity.CommunityPostVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommunityBoardDTO {

    Long idx;

    String name;

    public static CommunityBoardDTO fromEntity(CommunityBoardVO vo){
        return CommunityBoardDTO.builder()
                .idx(vo.getIdx())
                .name(vo.getName())
                .build();
    }


    @Builder
    public CommunityBoardDTO(Long idx, String name) {
        this.idx = idx;
        this.name = name;
    }
}

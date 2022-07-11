package com.itudy.api.domain.community.dto;

import com.itudy.api.domain.community.entity.CommunityPostVO;
import com.itudy.api.domain.user.dto.UserDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommunityPostDTO {

    Long idx;

    String title;

    String content;

    LocalDateTime createdAt;

    Long view;

    UserDTO user;

    public static CommunityPostDTO fromEntity(CommunityPostVO vo){
        return CommunityPostDTO.builder()
                .idx(vo.getIdx())
                .title(vo.getTitle())
                .content(vo.getContent())
                .createdAt(vo.getCreatedAt())
                .view(vo.getView())
                .user(UserDTO.fromEntitySimple(vo.getUser()))
                .build();
    }

    @Builder
    public CommunityPostDTO(Long idx, String title, String content, LocalDateTime createdAt, Long view, UserDTO user) {
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.view = view;
        this.user = user;
    }
}

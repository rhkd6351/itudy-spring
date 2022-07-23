package com.itudy.api.domain.community.dto;

import com.itudy.api.domain.community.entity.CommunityCommentVO;
import com.itudy.api.domain.user.dto.UserDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommunityCommentDTO {

    Long idx;
    String content;
    LocalDateTime createdAt;
    List<CommunityCommentDTO> childComments = new ArrayList<>();
    UserDTO user;


    public static CommunityCommentDTO fromEntity(
            CommunityCommentVO vo
    ){
        return CommunityCommentDTO.builder()
                .idx(vo.getIdx())
                .content(vo.getContent())
                .createdAt(vo.getCreatedAt())
                .childComments(vo.getChildComments().stream().map(
                        CommunityCommentDTO::fromEntityWithoutChild
                ).toList())
                .user(UserDTO.fromEntitySimple(vo.getUser()))
                .build();
    }

    public static CommunityCommentDTO fromEntityWithoutChild(CommunityCommentVO vo){
        return CommunityCommentDTO.builder()
                .idx(vo.getIdx())
                .content(vo.getContent())
                .createdAt(vo.getCreatedAt())
                .user(UserDTO.fromEntitySimple(vo.getUser()))
                .build();
    }

    @Builder
    public CommunityCommentDTO(Long idx, String content, LocalDateTime createdAt, List<CommunityCommentDTO> childComments, UserDTO user) {
        this.idx = idx;
        this.content = content;
        this.createdAt = createdAt;
        this.childComments = childComments;
        this.user = user;
    }
}

package com.itudy.api.domain.user.dto;

import com.itudy.api.domain.user.domain.UserVO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO {

    Long idx;
    String email;
    String nickname;
    String oauth;
    String imageUrl;
    LocalDateTime createdAt;


    public static UserDTO fromEntitySimple(UserVO vo){
        return UserDTO.builder()
                .idx(vo.getIdx())
                .nickname(vo.getNickname())
                .imageUrl(vo.getImageUrl())
                .build();
    }

    public static UserDTO fromEntityDetail(UserVO vo){
        UserDTO dto = UserDTO.fromEntitySimple(vo);
        dto.setEmail(vo.getEmail());
        dto.setOauth(vo.getOauth());
        dto.setCreatedAt(vo.getCreatedAt());

        return dto;
    }

    @Builder
    public UserDTO(Long idx, String email, String nickname, String oauth, String imageUrl, LocalDateTime createdAt) {
        this.idx = idx;
        this.email = email;
        this.nickname = nickname;
        this.oauth = oauth;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }
}

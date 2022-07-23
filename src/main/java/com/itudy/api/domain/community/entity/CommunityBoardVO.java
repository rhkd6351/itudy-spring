package com.itudy.api.domain.community.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "COMMUNITY_BOARD_TB")
public class CommunityBoardVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @Column(name = "name", length = 45)
    String name;

    @OneToMany(mappedBy = "board")
    List<CommunityPostVO> posts;

    @Builder
    public CommunityBoardVO(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

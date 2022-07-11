package com.itudy.api.domain.community.entity;

import com.itudy.api.domain.user.domain.UserVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "COMMUNITY_POST_TB")
public class CommunityPostVO {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long idx;

    @Column(name = "name", length = 200)
    String title;

    @Lob
    @Column(name = "content")
    String content;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "view")
    Long view;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    UserVO user;

    @ManyToOne
    @JoinColumn(name = "board_fk")
    CommunityBoardVO board;

    @Builder
    public CommunityPostVO(String title, String content, Long view, UserVO user, CommunityBoardVO board) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.user = user;
        this.board = board;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

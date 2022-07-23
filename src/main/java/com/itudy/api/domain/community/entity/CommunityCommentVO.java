package com.itudy.api.domain.community.entity;

import com.itudy.api.domain.user.domain.UserVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "COMMUNITY_COMMENT_TB")
public class CommunityCommentVO {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @Lob
    @Column(name = "content")
    String content;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    UserVO user;

    @ManyToOne
    @JoinColumn(name = "post_fk")
    CommunityPostVO post;

    @ManyToOne
    @JoinColumn(name = "upper_fk")
    CommunityCommentVO upperComment;

    @OneToMany(mappedBy = "upperComment")
    List<CommunityCommentVO> childComments;

    @Builder
    public CommunityCommentVO(String content, UserVO user, CommunityPostVO post, CommunityCommentVO upperComment) {
        this.content = content;
        this.user = user;
        this.post = post;
        this.upperComment = upperComment;
    }

    public void update(String content) {
        this.content = content;
    }
}

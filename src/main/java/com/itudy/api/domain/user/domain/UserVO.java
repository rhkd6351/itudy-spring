package com.itudy.api.domain.user.domain;


import com.itudy.api.domain.study.domain.StudyMemberVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "USER_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    String email;

    @Column(name = "nickname", nullable = false, unique = true, length = 50)
    String nickname;

    @Column(name = "oauth", nullable = false, unique = false, length = 50)
    String oauth;

    @Column(name = "activated", nullable = true, unique = false, length = 20)
    boolean activated;

    @Column(name = "refresh_token", nullable = true, unique = true, length = 255)
    String refreshToken;

    @Column(name = "fcm_token", nullable = true, unique = true, length = 255)
    String fcmToken;

    @Column(name = "image_url", nullable = false, unique = false, length = 255)
    String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @ManyToOne(targetEntity = AuthVO.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_fk", nullable = false, updatable = true)
    private AuthVO auth;


    @OneToMany(mappedBy = "user")
    List<StudyMemberVO> studies = new ArrayList<>();


    @Builder
    public UserVO(String email, String nickname, String oauth, boolean activated, AuthVO auth, String imageUrl) {
        this.email = email;
        this.nickname = nickname;
        this.oauth = oauth;
        this.activated = activated;
        this.auth = auth;
        this.imageUrl = imageUrl;
    }

    public enum Oauth {
        KAKAO("kakao"),
        GOOGLE("google"),
        FACEBOOK("facebook");

        final String value;

        Oauth(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

package com.itudy.api.domain.user.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


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

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @ManyToOne(targetEntity = AuthVO.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_fk", nullable = false, updatable = true)
    private AuthVO auth;

}

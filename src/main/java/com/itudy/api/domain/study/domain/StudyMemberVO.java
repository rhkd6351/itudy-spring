package com.itudy.api.domain.study.domain;

import com.itudy.api.domain.user.domain.UserVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "STUDY_MEMBER_TB")
@IdClass(StudyMemberID.class)
public class StudyMemberVO implements Serializable {

    @Id
    @JoinColumn(name = "study_fk")
    @ManyToOne(targetEntity = StudyVO.class)
    StudyVO study;

    @Id
    @JoinColumn(name = "user_fk")
    @ManyToOne(targetEntity = UserVO.class)
    UserVO user;

    @Column(name = "role", unique = false, nullable = false, length = 45)
    String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_fk")
    PositionVO position;

    @Builder
    public StudyMemberVO(StudyVO study, UserVO user, String role, PositionVO position) {
        this.study = study;
        this.user = user;
        this.role = role;
        this.position = position;
    }

    public enum Role{
        LEADER("leader"),
        MEMBER("member");

        public final String role;

        Role(String role) {
            this.role = role;
        }
    }
}

package com.itudy.api.domain.study.domain;

import com.itudy.api.domain.user.domain.UserVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "STUDY_MEMBER_TB")
public class StudyMemberVO implements Serializable {

    @Id
    @JoinColumn(name = "study_fk")
    @ManyToOne(targetEntity = StudyVO.class)
    StudyVO study;

    @Id
    @JoinColumn(name = "user_fk")
    @ManyToOne(targetEntity = UserVO.class)
    UserVO user;

    @Column(name = "role", unique = true, nullable = false, length = 45)
    String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_fk")
    PositionVO position;
}

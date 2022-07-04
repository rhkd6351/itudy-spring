package com.itudy.api.domain.study.domain;

import com.itudy.api.domain.user.domain.UserVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "STUDY_TB")
public class StudyVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @Column(name = "name", unique = true, nullable = false, length = 45)
    String name;

    @Column(name = "description", unique = true, nullable = false, length = 500)
    String description;

    @Column(name = "image_url", nullable = false, unique = false, length = 255)
    String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    List<StudyFieldMapping> fieldMappings = new ArrayList<>();

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    Set<StudyMemberVO> members = new HashSet<>();

    @Builder
    public StudyVO(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public void addMember(PositionVO position, String name, UserVO user) {
        this.members.add(StudyMemberVO.builder()
                .position(position)
                .role(name)
                .user(user)
                .study(this)
                .build());
    }

    public void addField(FieldVO field) {
        this.fieldMappings.add(StudyFieldMapping.builder()
                .field(field)
                .study(this)
                .build());
    }
}

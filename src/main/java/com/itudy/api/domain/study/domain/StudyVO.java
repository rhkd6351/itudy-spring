package com.itudy.api.domain.study.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "study")
    List<StudyFieldMapping> fieldMappings = new ArrayList<>();

    @OneToMany(mappedBy = "study")
    List<StudyMemberVO> members = new ArrayList<>();

}

package com.itudy.api.domain.study.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "FIELD_TB")
public class FieldVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @Column(name = "name", unique = true, nullable = false, length = 45)
    String name;

    @Column(name = "image_url", nullable = false, unique = false, length = 255)
    String imageUrl;

    @OneToMany(mappedBy = "field")
    List<StudyFieldMapping> fieldMappings = new ArrayList<>();

    @Builder
    public FieldVO(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}

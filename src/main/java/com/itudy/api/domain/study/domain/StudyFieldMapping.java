package com.itudy.api.domain.study.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "STUDY_FIELD_MAPPING")
public class StudyFieldMapping implements Serializable {

    @Id
    @JoinColumn(name = "study_fk")
    @ManyToOne(targetEntity = StudyVO.class)
    StudyVO study;

    @Id
    @JoinColumn(name = "field_fk")
    @ManyToOne(targetEntity = FieldVO.class)
    FieldVO field;

    @Builder
    public StudyFieldMapping(StudyVO study, FieldVO field) {
        this.study = study;
        this.field = field;
    }
}

package com.itudy.api.domain.portfolio.entity;

import com.itudy.api.domain.study.domain.FieldVO;
import com.itudy.api.domain.study.domain.StudyVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "PROJECT_TECH_MAPPING")
public class ProjectTechMapping implements Serializable {

    @Id
    @JoinColumn(name = "project_fk")
    @ManyToOne(targetEntity = ProjectVO.class)
    ProjectVO project;

    @Id
    @JoinColumn(name = "tech_fk")
    @ManyToOne(targetEntity = TechVO.class)
    TechVO tech;

    @Builder
    public ProjectTechMapping(ProjectVO project, TechVO tech) {
        this.project = project;
        this.tech = tech;
    }
}

package com.itudy.api.domain.portfolio.entity;

import com.itudy.api.domain.study.domain.RecruitmentTechMapping;
import com.itudy.api.domain.study.domain.StudyFieldMapping;
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
@Table(name = "TECH_TB")
public class TechVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @Column(name = "name", unique = true, nullable = false, length = 45)
    String name;

    @Column(name = "image_url", nullable = false, unique = false, length = 255)
    String imageUrl;

    @OneToMany(mappedBy = "tech")
    List<ProjectTechMapping> projects;

    @OneToMany(mappedBy = "tech")
    List<PortfolioTechMapping> portfolios;

    @OneToMany(mappedBy = "tech", fetch = FetchType.LAZY)
    List<RecruitmentTechMapping> techs;

    @Builder
    public TechVO(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}

package com.itudy.api.domain.study.domain;

import com.itudy.api.domain.portfolio.entity.TechVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "RECRUITMENT_TECH_MAPPING")
public class RecruitmentTechMapping implements Serializable {

    @Id
    @JoinColumn(name = "recruitment_fk")
    @ManyToOne(targetEntity = RecruitmentVO.class)
    RecruitmentVO recruitment;

    @Id
    @JoinColumn(name = "tech_fk")
    @ManyToOne(targetEntity = TechVO.class)
    TechVO tech;

    @Builder
    public RecruitmentTechMapping(RecruitmentVO recruitment, TechVO tech) {
        this.recruitment = recruitment;
        this.tech = tech;
    }
}

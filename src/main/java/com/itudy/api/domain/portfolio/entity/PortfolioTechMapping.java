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
@Table(name = "PORTFOLIO_TECH_MAPPING")
public class PortfolioTechMapping implements Serializable {

    @Id
    @JoinColumn(name = "portfolio_fk")
    @ManyToOne(targetEntity = PortfolioVO.class)
    PortfolioVO portfolio;

    @Id
    @JoinColumn(name = "tech_fk")
    @ManyToOne(targetEntity = TechVO.class)
    TechVO tech;

    @Builder
    public PortfolioTechMapping(PortfolioVO portfolio, TechVO tech) {
        this.portfolio = portfolio;
        this.tech = tech;
    }
}

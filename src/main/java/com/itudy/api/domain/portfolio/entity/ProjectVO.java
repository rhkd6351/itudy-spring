package com.itudy.api.domain.portfolio.entity;

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
@Table(name = "PROJECT_TB")
public class ProjectVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @Column(name = "title", unique = false, nullable = false, length = 200)
    String title;

    @Column(name = "description", nullable = false, unique = false, length = 500)
    String description;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    List<ProjectTechMapping> techs = new ArrayList<>();

    @ManyToOne(targetEntity = PortfolioVO.class)
    @JoinColumn(name = "portfolio_fk")
    PortfolioVO portfolio;

    @Builder
    public ProjectVO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void addTech(TechVO tech) {
        this.techs.add(
                ProjectTechMapping.builder()
                        .project(this)
                        .tech(tech)
                        .build()
        );
    }

    public void setPortfolio(PortfolioVO portfolioVO) {
        this.portfolio = portfolioVO;
    }
}

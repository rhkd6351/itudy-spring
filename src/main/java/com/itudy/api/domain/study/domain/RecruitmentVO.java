package com.itudy.api.domain.study.domain;

import com.itudy.api.domain.portfolio.entity.PortfolioVO;
import com.itudy.api.domain.portfolio.entity.TechVO;
import lombok.AccessLevel;
import lombok.Builder;
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
@Table(name = "RECRUITMENT_TB")
public class RecruitmentVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @Column(name = "title", unique = false, nullable = false, length = 255)
    String title;

    @Column(name = "description", unique = false, nullable = false, length = 500)
    String description;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "hits", unique = false, nullable = false)
    Long hits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_fk")
    StudyVO study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_fk")
    PortfolioVO portfolio;

    @OneToMany(mappedBy = "recruitment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<PositionDemandVO> demands = new ArrayList<>();;

    @OneToMany(mappedBy = "recruitment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<RecruitmentTechMapping> techs = new ArrayList<>();

    @Builder
    public RecruitmentVO(String title, Long hits, String description, StudyVO study, PortfolioVO portfolio) {
        this.title = title;
        this.description = description;
        this.study = study;
        this.portfolio = portfolio;
        this.hits = hits;
    }

    public void addTech(TechVO tech) {
        this.techs.add(RecruitmentTechMapping.builder()
                .tech(tech)
                .recruitment(this)
                .build());
    }

    public void addDemand(PositionVO position, Long demand) {
        this.demands.add(PositionDemandVO.builder()
                .position(position)
                .recruitment(this)
                .demand(demand)
                .build());
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void removeTechs() {
        this.techs.clear();
    }

    public void removeDemands() {
        this.demands.clear();
    }
}

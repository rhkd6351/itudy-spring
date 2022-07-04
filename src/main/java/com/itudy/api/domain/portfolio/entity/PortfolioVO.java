package com.itudy.api.domain.portfolio.entity;

import com.itudy.api.domain.study.domain.PositionVO;
import com.itudy.api.domain.user.domain.UserVO;
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
@Table(name = "PORTFOLIO_TB")
public class PortfolioVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idx;

    @Column(name = "title", unique = false, nullable = false, length = 200)
    String title;

    @Column(name = "description", nullable = false, unique = false, length = 500)
    String description;

    @Column(name = "file", nullable = true, unique = false, length = 255)
    String file;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @ManyToOne(targetEntity = PositionVO.class)
    @JoinColumn(name = "position_fk")
    PositionVO position;

    @ManyToOne(targetEntity = UserVO.class)
    @JoinColumn(name = "user_fk")
    UserVO user;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    List<PortfolioTechMapping> techs = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    List<ProjectVO> projects = new ArrayList<>();

    @Builder
    public PortfolioVO(String title, String description, PositionVO position, UserVO user, String file) {
        this.title = title;
        this.description = description;
        this.position = position;
        this.user = user;
        this.file = file;
    }

    public void addTech(TechVO tech) {
        this.techs.add(PortfolioTechMapping.builder()
                .tech(tech)
                .portfolio(this)
                .build());
    }

    public void addProject(ProjectVO project) {
        this.projects.add(project);
        project.setPortfolio(this);
    }
}

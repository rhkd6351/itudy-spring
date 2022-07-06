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
@Table(name = "POSITION_DEMAND_TB")
public class PositionDemandVO implements Serializable {

    @Id
    @JoinColumn(name = "position_fk")
    @ManyToOne(targetEntity = PositionVO.class)
    PositionVO position;

    @Id
    @JoinColumn(name = "recruitment_fk")
    @ManyToOne(targetEntity = RecruitmentVO.class)
    RecruitmentVO recruitment;

    @Column(name = "demand")
    Long demand;

    @Builder
    public PositionDemandVO(PositionVO position, RecruitmentVO recruitment, Long demand) {
        this.position = position;
        this.recruitment = recruitment;
        this.demand = demand;
    }
}

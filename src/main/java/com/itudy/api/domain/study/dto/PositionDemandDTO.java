package com.itudy.api.domain.study.dto;

import com.itudy.api.domain.study.domain.PositionDemandVO;
import com.itudy.api.domain.study.domain.PositionVO;
import com.itudy.api.util.Enum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.text.Position;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PositionDemandDTO implements Serializable {

    @Enum(enumClass = PositionVO.Name.class, ignoreCase = false)
    String position;

    @Positive
    Long demand;

    @Builder
    public PositionDemandDTO(String position, Long demand) {
        this.position = position;
        this.demand = demand;
    }

    public static PositionDemandDTO fromEntity(PositionDemandVO vo) {

        return PositionDemandDTO.builder()
                .position(vo.getPosition().getName())
                .demand(vo.getDemand())
                .build();

    }
}

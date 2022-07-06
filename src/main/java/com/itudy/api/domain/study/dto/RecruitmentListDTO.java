package com.itudy.api.domain.study.dto;

import com.itudy.api.domain.portfolio.dto.TechDTO;
import com.itudy.api.domain.study.domain.RecruitmentVO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecruitmentListDTO {

    Long idx;
    String title;
    String description;
    StudyDTO study;
    List<PositionDemandDTO> demands = new ArrayList<>();
    List<TechDTO> techs = new ArrayList<>();

    @Builder
    public RecruitmentListDTO(Long idx, String title, String description, StudyDTO study, List<PositionDemandDTO> demands, List<TechDTO> techs) {
        this.idx = idx;
        this.title = title;
        this.description = description;
        this.study = study;
        this.demands = demands;
        this.techs = techs;
    }

    public static RecruitmentListDTO fromEntity(RecruitmentVO vo){
        return RecruitmentListDTO.builder()
                .idx(vo.getIdx())
                .title(vo.getTitle())
                .description(vo.getDescription())
                .study(StudyDTO.fromEntity(vo.getStudy()))
                .demands(vo.getDemands().stream().distinct().map(PositionDemandDTO::fromEntity).toList())
                .techs(vo.getTechs().stream().distinct().map(i -> TechDTO.fromEntity(i.getTech())).toList())
                .build();
    }

}

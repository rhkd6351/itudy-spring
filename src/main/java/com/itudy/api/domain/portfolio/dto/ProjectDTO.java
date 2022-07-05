package com.itudy.api.domain.portfolio.dto;

import com.itudy.api.domain.portfolio.entity.ProjectVO;
import com.itudy.api.domain.portfolio.entity.TechVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProjectDTO {

    Long idx;

    @NotNull
    String title;

    @NotNull
    String description;

    @Valid
    List<TechDTO> techs;

    @Builder
    public ProjectDTO(Long idx, String title, String description, List<TechDTO> techs) {
        this.idx = idx;
        this.title = title;
        this.description = description;
        this.techs = techs;
    }

    public static ProjectDTO fromEntity(ProjectVO vo) {
        return ProjectDTO.builder()
                .idx(vo.getIdx())
                .title(vo.getTitle())
                .description(vo.getDescription())
                .techs(vo.getTechs().stream().map(i -> TechDTO.fromEntity(i.getTech())).toList())
                .build();

    }
}

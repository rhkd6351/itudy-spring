package com.itudy.api.domain.portfolio.dto;

import com.itudy.api.domain.portfolio.entity.PortfolioVO;
import com.itudy.api.domain.user.dto.UserDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PortfolioDTO {

    Long idx;

    String title;

    String description;

    String file;

    String position;

    LocalDateTime createdAt;

    List<TechDTO> techs;

    List<ProjectDTO> projects;

    UserDTO user;

    @Builder
    public PortfolioDTO(Long idx, String title, String description, String file, String position, LocalDateTime createdAt, List<TechDTO> techs, List<ProjectDTO> projects, UserDTO user) {
        this.idx = idx;
        this.title = title;
        this.description = description;
        this.file = file;
        this.position = position;
        this.createdAt = createdAt;
        this.techs = techs;
        this.projects = projects;
        this.user = user;
    }

    public static PortfolioDTO fromEntity(PortfolioVO vo) {

        return PortfolioDTO.builder()
                .idx(vo.getIdx())
                .title(vo.getTitle())
                .description(vo.getDescription())
                .file(vo.getFile())
                .position(vo.getPosition().getName())
                .createdAt(vo.getCreatedAt())
                .techs(vo.getTechs().stream().map(i -> TechDTO.fromEntity(i.getTech())).toList())
                .projects(vo.getProjects().stream().distinct().map(ProjectDTO::fromEntity).toList())
                .build();
    }
}

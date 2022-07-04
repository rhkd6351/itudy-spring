package com.itudy.api.domain.portfolio.dto;

import com.itudy.api.domain.portfolio.entity.TechVO;
import lombok.AccessLevel;
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

}

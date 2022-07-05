package com.itudy.api.domain.portfolio.dto;

import com.itudy.api.domain.study.domain.PositionVO;
import com.itudy.api.util.Enum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SavePortfolioRequest {

    Long idx;

    @NotNull
    String title;

    @NotNull
    String description;

    @Valid
    List<ProjectDTO> projects;

    List<Long> techIds;

    @Enum(enumClass = PositionVO.Name.class, ignoreCase = true)
    String position;

}

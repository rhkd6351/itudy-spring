package com.itudy.api.domain.study.dto;

import com.itudy.api.domain.study.domain.PositionVO;
import com.itudy.api.util.Enum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.text.Position;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRecruitmentRequest {

    @NotBlank
    String title;

    @NotNull
    String description;

    @NotNull
    Long portfolioIdx;

    @NotNull
    List<Long> techIds;

    @NotNull
    @Valid
    List<PositionDemandDTO> demands;
}

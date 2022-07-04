package com.itudy.api.domain.portfolio.dto;

import com.itudy.api.domain.portfolio.entity.TechVO;
import com.itudy.api.domain.study.domain.FieldVO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TechDTO {

    @NotNull
    Long idx;
    String name;
    String imageUrl;

    @Builder
    public TechDTO(Long idx, String name, String imageUrl) {
        this.idx = idx;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public static TechDTO fromEntity(TechVO tech){
        return TechDTO.builder()
                .idx(tech.getIdx())
                .name(tech.getName())
                .imageUrl(tech.getImageUrl())
                .build();
    }
}

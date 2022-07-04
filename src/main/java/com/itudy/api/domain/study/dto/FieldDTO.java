package com.itudy.api.domain.study.dto;

import com.itudy.api.domain.study.domain.FieldVO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldDTO {

    Long idx;
    String name;
    String imageUrl;

    @Builder
    public FieldDTO(Long idx, String name, String imageUrl) {
        this.idx = idx;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public static FieldDTO fromEntity(FieldVO field){
        return FieldDTO.builder()
                .idx(field.getIdx())
                .name(field.getName())
                .imageUrl(field.getImageUrl())
                .build();
    }
}

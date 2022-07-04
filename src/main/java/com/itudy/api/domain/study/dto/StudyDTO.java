package com.itudy.api.domain.study.dto;

import com.itudy.api.domain.study.domain.StudyVO;
import com.itudy.api.domain.user.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class StudyDTO {

    Long idx;
    String name;
    String description;
    String imageUrl;
    LocalDateTime createdAt;
    List<FieldDTO> fields;
    List<UserDTO> members;

    @Builder
    public StudyDTO(Long idx, String name, String description, String imageUrl, LocalDateTime createdAt, List<FieldDTO> fields, List<UserDTO> members) {
        this.idx = idx;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.fields = fields;
        this.members = members;
    }

    public static StudyDTO fromEntity(StudyVO vo) {
        return StudyDTO.builder()
                .idx(vo.getIdx())
                .name(vo.getName())
                .description(vo.getDescription())
                .imageUrl(vo.getImageUrl())
                .createdAt(vo.getCreatedAt())
                .fields(vo.getFieldMappings().stream().map(
                        i -> FieldDTO.fromEntity(i.getField())
                ).collect(Collectors.toList()))
                .members(vo.getMembers().stream().map(
                        i -> UserDTO.fromEntitySimple(i.getUser())
                ).collect(Collectors.toList()))
                .build();
    }
}

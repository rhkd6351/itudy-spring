package com.itudy.api.common.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "COMMON_IMAGE_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonImageVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", updatable = false, nullable = false)
    private Long idx;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Embedded
    private FileInfo fileInfo;

    @Builder
    public CommonImageVO(Long idx, String name, LocalDateTime createdAt, FileInfo fileInfo) {
        this.idx = idx;
        this.name = name;
        this.createdAt = createdAt;
        this.fileInfo = fileInfo;
    }
}


package com.itudy.api.domain.study.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "POSITION_TB")
public class PositionVO {

    @Id
    @Column(name = "name", unique = true, nullable = false, length = 45)
    String name;

}

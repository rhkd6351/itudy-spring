package com.itudy.api.domain.study.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "POSITION_TB")
public class PositionVO {

    @Id
    @Column(name = "name", unique = true, nullable = false, length = 45)
    String name;

    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    List<PositionDemandVO> recruits;

    public enum Name{
        DEVELOPER("developer"),
        PM("pm"),
        DESIGNER("designer"),;

        public final String name;

        Name(String name) {
            this.name = name;
        }
    }

}

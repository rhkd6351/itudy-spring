package com.itudy.api.domain.study.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class StudyMemberID implements Serializable {
    private Long study;
    private Long user;
}

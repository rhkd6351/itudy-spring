package com.itudy.api.domain.study.repository;

import com.itudy.api.domain.study.domain.RecruitmentVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRepository extends JpaRepository<RecruitmentVO, Long> {


}

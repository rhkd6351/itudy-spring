package com.itudy.api.domain.study.repository;

import com.itudy.api.domain.study.domain.RecruitmentVO;
import com.itudy.api.domain.study.domain.StudyVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRepository extends JpaRepository<RecruitmentVO, Long> {

    @EntityGraph(attributePaths = {"study", "study.fieldMappings", "demands", "techs"})
    public Page<RecruitmentVO> findByStudy(StudyVO study, Pageable pageable);

}

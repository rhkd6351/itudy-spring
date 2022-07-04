package com.itudy.api.domain.study.repository;

import com.itudy.api.domain.study.domain.StudyMemberID;
import com.itudy.api.domain.study.domain.StudyMemberVO;
import com.itudy.api.domain.user.domain.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMemberVO, StudyMemberID> {

    @Query(value = "select m from StudyMemberVO m where m.user = :user")
    @EntityGraph(attributePaths = {"study", "study.fieldMappings", "study.members"})
    Page<StudyMemberVO> findByUser(UserVO user, Pageable pageable);

}

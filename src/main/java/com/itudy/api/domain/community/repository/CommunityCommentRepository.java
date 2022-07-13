package com.itudy.api.domain.community.repository;

import com.itudy.api.domain.community.entity.CommunityCommentVO;
import com.itudy.api.domain.community.entity.CommunityPostVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityCommentRepository extends JpaRepository<CommunityCommentVO, Long> {

    @EntityGraph(attributePaths = {"childComments"})
    @Query(value = "select c from CommunityCommentVO c where c.post = :post and c.upperComment is null")
    Page<CommunityCommentVO> findByPostNotContainingParent(CommunityPostVO post, Pageable pageable);
}

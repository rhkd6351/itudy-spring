package com.itudy.api.domain.community.repository;

import com.itudy.api.domain.community.entity.CommunityBoardVO;
import com.itudy.api.domain.community.entity.CommunityPostVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostRepository extends JpaRepository<CommunityPostVO, Long> {

    Page<CommunityPostVO> findByBoard(CommunityBoardVO board, Pageable pageable);
}

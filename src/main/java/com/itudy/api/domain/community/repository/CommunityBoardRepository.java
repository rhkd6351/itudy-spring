package com.itudy.api.domain.community.repository;

import com.itudy.api.domain.community.entity.CommunityBoardVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoardVO, Long> {

}

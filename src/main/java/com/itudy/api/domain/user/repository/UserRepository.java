package com.itudy.api.domain.user.repository;


import com.itudy.api.domain.user.domain.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {

    public Optional<UserVO> findByEmail(String email);

}

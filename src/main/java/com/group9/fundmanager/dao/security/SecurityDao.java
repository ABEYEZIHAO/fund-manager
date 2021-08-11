package com.group9.fundmanager.dao.security;

import com.group9.fundmanager.pojo.Position;
import com.group9.fundmanager.pojo.Security;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityDao extends JpaRepository<Security,Long> {
    Optional<Position> findPositionBySecurityId(Long securityId);
}

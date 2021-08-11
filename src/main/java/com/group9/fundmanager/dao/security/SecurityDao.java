package com.group9.fundmanager.dao.security;

import com.group9.fundmanager.pojo.Manager;
import com.group9.fundmanager.pojo.Security;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author abe
 */
public interface SecurityDao extends JpaRepository<Security, Long>{
    /**
     * Find a security with the target name
     * @param symbol name of a security
     * @return the security with the specific name
     */
    Optional<Security> findSecurityBySymbol(String symbol);
}

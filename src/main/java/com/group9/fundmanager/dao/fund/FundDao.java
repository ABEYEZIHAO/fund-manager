package com.group9.fundmanager.dao.fund;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group9.fundmanager.pojo.Fund;

import java.util.Optional;

/**
 * @author dennis
 */
public interface FundDao extends JpaRepository<Fund, Long>{
    /**
     * Find a fund with the target name
     * @param name name of a fund
     * @return the fund with the specific name
     */
    Optional<Fund> findFundByName(String name);
}

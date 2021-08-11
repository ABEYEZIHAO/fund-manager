package com.group9.fundmanager.dao.fund;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group9.fundmanager.pojo.Fund;

public interface FundDao extends JpaRepository<Fund, Long>{

}

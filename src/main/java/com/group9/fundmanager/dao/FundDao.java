package com.group9.fundmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group9.fundmanager.pojo.Fund;

public interface FundDao extends JpaRepository<Fund,Integer>{

}

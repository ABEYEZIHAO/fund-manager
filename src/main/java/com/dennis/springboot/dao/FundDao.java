package com.dennis.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dennis.springboot.pojo.Fund;

public interface FundDao extends JpaRepository<Fund,Integer>{

}

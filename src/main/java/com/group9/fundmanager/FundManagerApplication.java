package com.group9.fundmanager;
 
import com.group9.fundmanager.dao.fund.FundDao;
import com.group9.fundmanager.pojo.Fund;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FundManagerApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(FundManagerApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(FundDao fundDao) {
//        return args -> {
//            List<Fund> users = com.sun.tools.javac.util.List.of(
//                    new Fund(1L,
//                            "Olympic Memorial Fund"),
//                    new Fund(2L,
//                            "UK Overseas Income Fund")
//            );
//
//            List<Fund> savedUsers = fundDao.saveAll(users);
//        };
//    }
}
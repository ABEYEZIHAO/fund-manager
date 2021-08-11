package com.group9.fundmanager;
 
import com.group9.fundmanager.dao.fund.FundDao;
import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.pojo.Fund;
import com.group9.fundmanager.pojo.Manager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FundManagerApplication {
 
    public static void main(String[] args) {
        ApplicationContext apc = SpringApplication.run(FundManagerApplication.class, args);
        System.out.println(apc);
    }

    @Bean
    CommandLineRunner commandLineRunner(FundDao fundDao, ManagerDao managerDao) {
        return args -> {
            List<Manager> managers = com.sun.tools.javac.util.List.of(
                    new Manager(1L,
                            "Terry",
                            "Jones",
                            new ArrayList<>()),
                    new Manager(2L,
                            "Mike",
                            "Smith",
                            new ArrayList<>())
            );

            List<Manager> savedManagers = managerDao.saveAll(managers);

            List<Fund> funds = com.sun.tools.javac.util.List.of(
                    new Fund(1L,
                            "Olympic Memorial Fund",
                            savedManagers.get(0),
                            new ArrayList<>()),
                    new Fund(2L,
                            "UK Overseas Income Fund",
                            savedManagers.get(1),
                            new ArrayList<>())
            );

            List<Fund> savedFunds = fundDao.saveAll(funds);

        };
    }
}
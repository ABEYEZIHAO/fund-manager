package com.group9.fundmanager;
 
import com.group9.fundmanager.dao.fund.FundDao;
import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.dao.position.PositionDao;
import com.group9.fundmanager.dao.security.SecurityDao;
import com.group9.fundmanager.pojo.Fund;
import com.group9.fundmanager.pojo.Manager;
import com.group9.fundmanager.pojo.Position;
import com.group9.fundmanager.pojo.Security;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FundManagerApplication {
 
    public static void main(String[] args) {
        ApplicationContext apc = SpringApplication.run(FundManagerApplication.class, args);
        System.out.println(apc);
    }

    @Bean
    CommandLineRunner commandLineRunner(FundDao fundDao, ManagerDao managerDao, SecurityDao securityDao, PositionDao positionDao) {
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

            List<Security> securities = com.sun.tools.javac.util.List.of(
                    new Security(1L,
                            "IBM"),
                    new Security(2L,
                            "AAPL"),
                    new Security(3L,
                            "C")
            );

            List<Security> savedSecurities = securityDao.saveAll(securities);

            List<Position> positions = com.sun.tools.javac.util.List.of(
                    new Position(1L,
                            savedSecurities.get(0),
                            100,
                            LocalDate.of(2016, 1, 10)),
                    new Position(2L,
                            savedSecurities.get(0),
                            250,
                            LocalDate.of(2016, 9, 23)),
                    new Position(3L,
                            savedSecurities.get(0),
                            200,
                            LocalDate.of(2016, 8, 14)),
                    new Position(4L,
                            savedSecurities.get(1),
                            125,
                            LocalDate.of(2016, 9, 23)),
                    new Position(5L,
                            savedSecurities.get(2),
                            75,
                            LocalDate.of(2017, 1, 27))
            );

            List<Position> savedPositions = positionDao.saveAll(positions);
        };
    }
}
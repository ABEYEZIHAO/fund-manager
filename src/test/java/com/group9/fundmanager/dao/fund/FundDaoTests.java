package com.group9.fundmanager.dao.fund;

import com.group9.fundmanager.pojo.Fund;
import com.group9.fundmanager.pojo.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FundDaoTests {
    @Autowired
    FundDao fundDao;

    List<Manager> defaultManagers = List.of(
            new Manager(1L,
                    "Terry",
                    "Jones",
                    new ArrayList<>()),
            new Manager(2L,
                    "Mike",
                    "Smith",
                    new ArrayList<>())
    );

    List<Fund> defaultFunds = List.of(
            new Fund(1L,
                    "Olympic Memorial Fund",
                    defaultManagers.get(0),
                    new ArrayList<>()),
            new Fund(2L,
                    "UK Overseas Income Fund",
                    defaultManagers.get(0),
                    new ArrayList<>()),
            new Fund(3L,
                    "North America Growth",
                    defaultManagers.get(1),
                    new ArrayList<>()),
            new Fund(4L,
                    "Global Tech Fund",
                    defaultManagers.get(1),
                    new ArrayList<>())
    );

    @BeforeEach
    public void bootstrapDatabase(){
        fundDao.deleteAll();
        fundDao.saveAll(defaultFunds);
    }

    @Test
    public void testSaveReadDeleteAll(){
        fundDao.deleteAll();
        List<Fund> funds = fundDao.findAll();
        assertEquals(0, funds.size());

        fundDao.saveAll(defaultFunds);
        funds = fundDao.findAll();
        assertEquals(defaultFunds.size(), funds.size());
    }

    @Test
    public void testFindFundByName() {
        Optional<Fund> omf = fundDao.findFundByName("Olympic Memorial Fund");
        assertTrue(omf.isPresent());
    }

    @Test
    public void testFindFundNameNotFound() {
        Optional<Fund> bnf = fundDao.findFundByName("Best Neueda Fund");
        assertFalse(bnf.isPresent());
    }
}

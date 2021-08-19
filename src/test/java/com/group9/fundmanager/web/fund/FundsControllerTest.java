package com.group9.fundmanager.web.fund;

import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.pojo.Fund;
import com.group9.fundmanager.pojo.Manager;
import com.group9.fundmanager.pojo.Position;
import com.group9.fundmanager.pojo.Security;
import com.group9.fundmanager.service.fund.FundService;
import com.group9.fundmanager.service.manager.ManagerService;
import com.group9.fundmanager.web.manager.ManagerController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FundsController.class)
class FundsControllerTest {

    @MockBean
    FundService fundService;

    @Autowired
    MockMvc mockMvc;

    List<Security> defaultSecurities = java.util.List.of(
            new Security(1L,
                    "IBM"),
            new Security(2L,
                    "AAPL"),
            new Security(3L,
                    "C")
    );

    List<Position> defaultPositions = java.util.List.of(
            new Position(1L,
                    defaultSecurities.get(0),
                    100,
                    LocalDate.of(2016, 1, 10),
                    null),
            new Position(2L,
                    defaultSecurities.get(0),
                    250,
                    LocalDate.of(2016, 9, 23),
                    null),
            new Position(3L,
                    defaultSecurities.get(0),
                    200,
                    LocalDate.of(2016, 8, 14),
                    null),
            new Position(4L,
                    defaultSecurities.get(1),
                    125,
                    LocalDate.of(2016, 9, 23),
                    null),
            new Position(5L,
                    defaultSecurities.get(2),
                    75,
                    LocalDate.of(2017, 1, 27),
                    null)
    );
    List<Manager> defaultManagers = java.util.List.of(
                    new Manager(1L,
                            "Terry",
                            "Jones",
                            new ArrayList<>()),
                    new Manager(2L,
                            "Mike",
                            "Smith",
                            new ArrayList<>())
            );
    List<Fund> defaultFunds = java.util.List.of(
                    new Fund(1L,
                            "Olympic Memorial Fund",
                            defaultManagers.get(0),
                            java.util.List.of(defaultPositions.get(0),
                                    defaultPositions.get(1))),
                    new Fund(2L,
                            "UK Overseas Income Fund",
                            defaultManagers.get(0),
                            java.util.List.of(defaultPositions.get(2))),
                    new Fund(3L,
                            "North America Growth",
                            defaultManagers.get(1),
                            java.util.List.of(defaultPositions.get(3))),
                    new Fund(4L,
                            "Global Tech Fund",
                            defaultManagers.get(1),
                            java.util.List.of(defaultPositions.get(4)))
            );

    @Test
    void listFund() {
    }

    @Test
    void addFund() {
    }

    @Test
    void deleteFund() {
    }

    @Test
    void updateFund() {
    }

    @Test
    public void getFund() throws Exception{
        when(fundService.getFund(1L)).thenReturn(defaultFunds.get(0));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/funds/1"))
                .andExpect(status().isOk());

    }
}
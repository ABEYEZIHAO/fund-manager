package com.group9.fundmanager.web.position;

import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.dao.position.PositionDao;
import com.group9.fundmanager.dao.security.SecurityDao;
import com.group9.fundmanager.pojo.Position;
import com.group9.fundmanager.pojo.Security;
import com.group9.fundmanager.service.fund.FundService;
import com.group9.fundmanager.service.manager.ManagerService;
import com.group9.fundmanager.service.position.PositionService;
import com.group9.fundmanager.web.security.SecurityController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PositionController.class)
class PositionControllerTest {

    @MockBean
    PositionService positionService;

    @MockBean
    PositionDao positionDao;

    @MockBean
    SecurityDao securityDao;

    @Autowired
    MockMvc mockMvc;

    List<Security> securities = java.util.List.of(
            new Security(1L,
                    "IBM"),
            new Security(2L,
                    "AAPL"),
            new Security(3L,
                    "C")
    );



    List<Position> defaultPositions = java.util.List.of(
                    new Position(1L,
                            securities.get(0),
                            100,
                            LocalDate.of(2016, 1, 10),
                            null),
                    new Position(2L,
                            securities.get(0),
                            250,
                            LocalDate.of(2016, 9, 23),
                            null),
                    new Position(3L,
                            securities.get(0),
                            200,
                            LocalDate.of(2016, 8, 14),
                            null),
                    new Position(4L,
                            securities.get(1),
                            125,
                            LocalDate.of(2016, 9, 23),
                            null),
                    new Position(5L,
                            securities.get(2),
                            75,
                            LocalDate.of(2017, 1, 27),
                            null)
            );

    @Test
    void listSecurity() {
    }

    @Test
    void addPosition() {
    }

    @Test
    void deletePosition() {
    }

    @Test
    void updatePosition() {
    }

    @Test
    public void getSecurity() throws Exception{
        when(positionService.getPosition(1L)).thenReturn(defaultPositions.get(0));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/positions/1"))
                .andExpect(status().isOk());

    }
}
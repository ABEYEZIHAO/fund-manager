package com.group9.fundmanager.service.fund;

import com.group9.fundmanager.dao.fund.FundDao;
import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.dao.position.PositionDao;
import com.group9.fundmanager.exception.EntityNotFoundException;
import com.group9.fundmanager.pojo.Fund;
import com.group9.fundmanager.pojo.Manager;
import com.group9.fundmanager.pojo.Position;
import com.group9.fundmanager.pojo.Security;
import com.group9.fundmanager.service.position.PositionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FundServiceTests {
    @Mock
    private FundDao fundDao;
//    @Mock
//    private PositionDao positionDao;

    @InjectMocks
    private FundService fundService;
//    @InjectMocks
//    private PositionService positionService;

    List<Security> defaultSecurities = List.of(
            new Security(1L,
                    "IBM"),
            new Security(2L,
                    "AAPL")
    );

    List<Position> defaultPositions = List.of(
            new Position(1L,
                    defaultSecurities.get(0),
                    100,
                    LocalDate.of(2016, 1, 10),
                    null),
            new Position(2L,
                    defaultSecurities.get(0),
                    250,
                    LocalDate.of(2016, 9, 23),
                    null)
    );

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
                    List.of(defaultPositions.get(0),
                            defaultPositions.get(1))),
            new Fund(2L,
                    "UK Overseas Income Fund",
                    defaultManagers.get(0),
                    List.of(defaultPositions.get(0))),
            new Fund(3L,
                    "North America Growth",
                    defaultManagers.get(1),
                    List.of(defaultPositions.get(1))),
            new Fund(4L,
                    "Global Tech Fund",
                    defaultManagers.get(1),
                    List.of(defaultPositions.get(0)))
    );

    @Test
    public void testGetFunds(){

        when(fundDao.findAll()).thenReturn(defaultFunds);

        List<Fund> funds = fundService.getFund();
        assertNotNull(funds);
        assertTrue(funds.size() > 0);
    }

    @Test
    public void testGetFundSuccess(){
        when(fundDao.findById(1L))
                .thenReturn(Optional.of(defaultFunds.get(0)));
        Fund fund = fundService.getFund(1L);
        assertNotNull(fund);
    }

    @Test
    public void testGetFundNotFound(){
        assertThrows(EntityNotFoundException.class,
                () -> fundService.getFund(0L));
    }

//    @Test
//    public void testAddNewFund(){
//        Fund newFund = new Fund(
//                5L,
//                "Test Fund",
//                defaultManagers.get(0),
//                List.of(defaultPositions.get(0))
//        );
//
//        when(positionDao.findAll()).thenReturn(defaultPositions);
//        List<Position> positions = positionService.getPosition();
//        System.out.println(positions);
//
//        fundService.addNewFund("Test Fund", 1L, 1L);
//        verify(fundDao).save(newFund);
//    }
}

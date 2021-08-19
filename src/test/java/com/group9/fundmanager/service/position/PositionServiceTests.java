package com.group9.fundmanager.service.position;

import com.group9.fundmanager.dao.position.PositionDao;
import com.group9.fundmanager.exception.EntityNotFoundException;
import com.group9.fundmanager.pojo.Position;
import com.group9.fundmanager.pojo.Security;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PositionServiceTests {
    @Mock
    private PositionDao positionDao;

    @InjectMocks
    private PositionService positionService;

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

    @Test
    public void testGetPositions () {
        when(positionDao.findAll()).thenReturn(defaultPositions);

        List<Position> positions = positionService.getPosition();
        assertNotNull(positions);
        assertTrue(positions.size() > 0);
    }

    @Test
    public void testGetPositionSuccess(){
        when(positionDao.findById(1L))
                .thenReturn(Optional.of(defaultPositions.get(0)));
        Position position = positionService.getPosition(1L);
        assertNotNull(position);
    }

    @Test
    public void testGetPositionNotFound(){
        assertThrows(EntityNotFoundException.class,
                () -> positionService.getPosition(0L));
    }
}

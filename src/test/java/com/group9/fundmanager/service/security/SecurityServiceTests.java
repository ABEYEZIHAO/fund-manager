package com.group9.fundmanager.service.security;

import com.group9.fundmanager.dao.position.PositionDao;
import com.group9.fundmanager.dao.security.SecurityDao;
import com.group9.fundmanager.exception.EntityNotFoundException;
import com.group9.fundmanager.pojo.Position;
import com.group9.fundmanager.pojo.Security;
import com.group9.fundmanager.service.position.PositionService;
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
public class SecurityServiceTests {
    @Mock
    private SecurityDao securityDao;

    @InjectMocks
    private SecurityService securityService;

    List<Security> defaultSecurities = List.of(
            new Security(1L,
                    "IBM"),
            new Security(2L,
                    "AAPL")
    );

    @Test
    public void testGetSecurities () {
        when(securityDao.findAll()).thenReturn(defaultSecurities);

        List<Security> securities = securityService.getSecurity();
        assertNotNull(securities);
        assertTrue(securities.size() > 0);
    }

    @Test
    public void testGetSecuritySuccess(){
        when(securityDao.findById(1L))
                .thenReturn(Optional.of(defaultSecurities.get(0)));
        Security security = securityService.getSecurity(1L);
        assertNotNull(security);
    }

    @Test
    public void testGetSecurityNotFound(){
        assertThrows(EntityNotFoundException.class,
                () -> securityService.getSecurity(0L));
    }
}

package com.group9.fundmanager.service.manager;

import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.exception.EntityNotFoundException;
import com.group9.fundmanager.pojo.Manager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ManagerServiceTests {
    @Mock
    private ManagerDao managerDao;

    @InjectMocks
    private ManagerService managerService;

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

    @Test
    public void testGetManagers(){

        when(managerDao.findAll()).thenReturn(defaultManagers);

        List<Manager> managers = managerService.getManager();
        assertNotNull(managers);
        assertTrue(managers.size() > 0);
    }

    @Test
    public void testGetManagerSuccess(){
        when(managerDao.findById(1L))
                .thenReturn(Optional.of(defaultManagers.get(0)));
        Manager manager = managerService.getManager(1L);
        assertNotNull(manager);
    }

    @Test
    public void testGetManagerNotFound(){
        assertThrows(EntityNotFoundException.class,
                () -> managerService.getManager(0L));
    }
}

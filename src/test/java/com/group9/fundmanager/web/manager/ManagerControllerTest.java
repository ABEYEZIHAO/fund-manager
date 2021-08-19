package com.group9.fundmanager.web.manager;

import com.group9.fundmanager.FundManagerApplication;
import com.group9.fundmanager.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.pojo.Manager;
import com.group9.fundmanager.service.fund.FundService;
import com.group9.fundmanager.service.manager.ManagerService;
import com.group9.fundmanager.web.fund.FundsController;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ManagerController.class)

public class ManagerControllerTest {

    @MockBean
    FundService fundService;
    @MockBean
    ManagerService managerService;

    @MockBean
    ManagerDao managerDao;

    @Autowired
    MockMvc mockMvc;

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

    @Test
    public void getManager() throws Exception{
        when(managerService.getManager(1L)).thenReturn(defaultManagers.get(0));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/managers/1"))
                .andExpect(status().isOk());

    }

//    @Test
//    public void testGetManagerNotFound() throws Exception{
//        when(managerService.getManager(1L)).thenReturn();
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/managers/1"))
//                .andExpect(status().isNotFound());
//
//    }



    @org.junit.jupiter.api.Test
    public void addManager() throws Exception{
        String first_name = "Terry";
        String last_name = "Jones";


        RequestBuilder request = MockMvcRequestBuilders
                .post("/managers")
                .param("first_name",first_name)
                .param("last_name",last_name)
                .contentType(MediaType.APPLICATION_JSON);


        mockMvc.perform(request)
                .andExpect(status().isOk());

        verify(managerService).addNewManager(first_name,last_name);
    }

    @Test
    public void updateManager() throws Exception{
        Long id = 1L;


    }
}

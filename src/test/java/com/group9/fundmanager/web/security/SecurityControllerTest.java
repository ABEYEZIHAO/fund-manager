package com.group9.fundmanager.web.security;

import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.dao.security.SecurityDao;
import com.group9.fundmanager.pojo.Manager;
import com.group9.fundmanager.pojo.Security;
import com.group9.fundmanager.service.fund.FundService;
import com.group9.fundmanager.service.manager.ManagerService;
import com.group9.fundmanager.service.security.SecurityService;
import com.group9.fundmanager.web.manager.ManagerController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SecurityController.class)
public class SecurityControllerTest {

    @MockBean
    SecurityService securityService;

    @MockBean
    SecurityDao securityDao;

    @MockBean
    Security security;
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

    @Test
    public void addSecurity() throws Exception{

        String symbol = "APP";
        security = new Security(
                "APP");

        RequestBuilder request = MockMvcRequestBuilders
                .post("/securities")
                .param("symbol",symbol)
                .contentType(MediaType.APPLICATION_JSON);


        mockMvc.perform(request)
                .andExpect(status().isOk());

        verify(securityService).addNewSecurity(security);
    }

    @Test
    public void getSecurity() throws Exception{
        when(securityService.getSecurity(1L)).thenReturn(defaultSecurities.get(0));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/securities/1"))
                .andExpect(status().isOk());

    }
}
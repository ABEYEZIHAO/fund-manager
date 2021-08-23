package com.group9.fundmanager.web.security;

import com.group9.fundmanager.pojo.Security;
import com.group9.fundmanager.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Abe
 */
@Controller
@RequestMapping(path = "api/v1/securities")
public class SecurityController {
    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping
    public List<Security> getSecurity() {
        return securityService.getSecurity();
    }

    @GetMapping("{id}")
    public Security getSecurity(@PathVariable("id") Long id) {
        return securityService.getSecurity(id);
    }

    @PostMapping
    public void addSecurity(Security newSecurity) {
        securityService.addNewSecurity(newSecurity);
    }

    @DeleteMapping("{id}")
    public void deleteSecurity(@PathVariable("id") Long id) {
        securityService.deleteSecurity(id);
    }

    @PutMapping("/securities/{id}")
    public void updateSecurity(Security newSecurity) {
        securityService.updateSecurity(newSecurity);
    }
}

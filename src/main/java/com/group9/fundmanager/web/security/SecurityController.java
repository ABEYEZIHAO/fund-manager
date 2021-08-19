package com.group9.fundmanager.web.security;

import com.group9.fundmanager.pojo.Security;
import com.group9.fundmanager.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Abe
 */
@Controller
public class SecurityController {
    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/securities")
    public String listSecurity(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        return securityService.listSecurity(m, start, size);
    }

    @PostMapping("/securities")
    public void addSecurity(Security newSecurity) {
        securityService.addNewSecurity(newSecurity);
//        return "redirect:securities";
    }

    @DeleteMapping("/securities/{id}")
    public String deleteSecurity(@PathVariable("id") Long id) {
        securityService.deleteSecurity(id);
        return "redirect:/securities";
    }

    @PutMapping("/securities/{id}")
    public String updateSecurity(Security newSecurity) {
        securityService.updateSecurity(newSecurity);
        return "redirect:/securities";
    }

    @GetMapping("/securities/{id}")
    public String getSecurity(@PathVariable("id") Long id, Model m) {
        Security c= securityService.getSecurity(id);
        m.addAttribute("c", c);
        return "editSecurity";
    }
}

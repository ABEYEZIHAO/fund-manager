package com.group9.fundmanager.web.manager;

import com.group9.fundmanager.pojo.Fund;
import com.group9.fundmanager.pojo.Manager;
import com.group9.fundmanager.service.fund.FundService;
import com.group9.fundmanager.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.jar.Manifest;

/**
 * @author abe
 */
@Controller
public class ManagerController {
    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/managers")
    public String listManager(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        return managerService.listManager(m, start, size);
    }

    @PostMapping("/managers")
    public String addManager(Manager newManager) throws Exception {
        managerService.addNewManager(newManager);
        return "redirect:managers";
    }

    @DeleteMapping("/managers/{id}")
    public String deleteManager(@PathVariable("id") Long id) throws Exception {
        managerService.deleteManager(id);
        return "redirect:managers";
    }

    @PutMapping("/managers/{id}")
    public String updateManager(Manager newManager) throws Exception {
        managerService.updateManager(newManager);
        return "redirect:managers";
    }

    @GetMapping("/managers/{id}")
    public String getManager(@PathVariable("id") Long id,Model m) throws Exception {
        Manager c= managerService.getManager(id);
        m.addAttribute("c", c);
        return "editManager";
    }
}

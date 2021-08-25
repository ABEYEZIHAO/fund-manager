package com.group9.fundmanager.web.manager;

import com.group9.fundmanager.pojo.Manager;
import com.group9.fundmanager.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Abe
 */
@RestController
@RequestMapping(path = "/api/v1/managers")
public class ManagerController {
    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public List<Manager> getManager() {
        return managerService.getManager();
    }

    @GetMapping("{id}")
    public Manager getManager(@PathVariable("id") Long id) {
        return managerService.getManager(id);
    }

    @PostMapping
    public void addManager(@RequestBody Manager newManager) {
        managerService.addNewManager(newManager);
    }

    @DeleteMapping("{id}")
    public void deleteManager(@PathVariable("id") Long id) {
        managerService.deleteManager(id);
    }

    @PutMapping("{id}")
    public void updateManager(@PathVariable("id") Long id, @RequestBody Manager updatedManager) {
        managerService.updateManager(id, updatedManager);
    }
}

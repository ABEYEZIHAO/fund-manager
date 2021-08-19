package com.group9.fundmanager.web.manager;

import com.group9.fundmanager.pojo.Manager;
import com.group9.fundmanager.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


/**
 * @author Abe
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
    public void addManager(WebRequest webRequest) {
        String[] firstNames = webRequest.getParameterValues("first_name");
        String[] lastNames = webRequest.getParameterValues("last_name");

        if (firstNames == null) {
            throw new IllegalArgumentException("Please input the first name");
        } else if (lastNames == null) {
            throw new IllegalArgumentException("Please input the last name");
        } else {
            managerService.addNewManager(firstNames[0], lastNames[0]);
//            return "redirect:managers";
        }
    }

    @DeleteMapping("/managers/{id}")
    public String deleteManager(@PathVariable("id") Long id) {
        managerService.deleteManager(id);
        return "redirect:/managers";
    }

    @PutMapping("/managers/{id}")
    public String updateManager(@PathVariable("id") Long id, WebRequest webRequest) throws Exception {
        String[] firstNames = webRequest.getParameterValues("first_name");
        String[] lastNames = webRequest.getParameterValues("last_name");

        if (firstNames == null) {
            throw new IllegalArgumentException("Please input the first name");
        } else if (lastNames == null) {
            throw new IllegalArgumentException("Please input the last name");
        } else {
            managerService.updateManager(id, firstNames[0], lastNames[0]);
            return "redirect:/managers";
        }
    }

    @GetMapping("/managers/{id}")
    public String getManager(@PathVariable("id") Long id, Model m) {
        Manager c= managerService.getManager(id);
        m.addAttribute("c", c);
        return "editManager";
    }
}

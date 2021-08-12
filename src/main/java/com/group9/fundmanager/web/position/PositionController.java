package com.group9.fundmanager.web.position;


import com.group9.fundmanager.pojo.Position;
import com.group9.fundmanager.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


@Controller
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/positions")
    public String listSecurity(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        return positionService.listPosition(m, start, size);
    }

    @PostMapping("/positions")
    public String addPosition(Position newPosition) throws Exception {
//        String[] names = webRequest.getParameterValues("name");
//        String[] managerIds = webRequest.getParameterValues("quantity");
//        if (names == null) {
//            throw new IllegalArgumentException("Please input the fund name.");
//        } else if (managerIds == null) {
//            throw new IllegalArgumentException("Please input the ID of an existing manager. Otherwise, please create the new manager first.");
//        } else {
            positionService.addNewPosition(newPosition);
            return "redirect:positions";

    }

    @DeleteMapping("/positions/{id}")
    public String deletePosition(@PathVariable("id") Long id) throws Exception {
        positionService.deletePosition(id);
        return "redirect:/positions";
    }

    @PutMapping("/positions/{id}")
    public String updatePosition(Position newPosition) throws Exception {
        positionService.updatePosition(newPosition);
        return "redirect:/positions";
    }

    @GetMapping("/positions/{id}")
    public String getSecurity(@PathVariable("id") Long id,Model m) throws Exception {
        Position c= positionService.getPosition(id);
        m.addAttribute("c", c);
        return "editPosition";
    }
}

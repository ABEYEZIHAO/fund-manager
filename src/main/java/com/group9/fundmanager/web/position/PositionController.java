package com.group9.fundmanager.web.position;


import com.group9.fundmanager.pojo.Position;
import com.group9.fundmanager.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Dennis
 */
@Controller
@RequestMapping(path = "/api/v1/positions")
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public List<Position> getSecurity() {
        return positionService.getPosition();
    }

    @GetMapping("{id}")
    public Position getSecurity(@PathVariable("id") Long id) {
       return positionService.getPosition(id);
    }

    @PostMapping
    public void addPosition(@RequestBody Position newPosition) {
        positionService.addNewPosition(newPosition);
    }

    @DeleteMapping("{id}")
    public void deletePosition(@PathVariable("id") Long id) {
        positionService.deletePosition(id);
    }
}

package com.group9.fundmanager.web.fund;
import com.group9.fundmanager.pojo.Fund;
import com.group9.fundmanager.service.fund.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dennis
 */
@RestController
@RequestMapping(path = "/api/v1/funds")
public class FundController {
    private final FundService fundService;

    @Autowired
    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @GetMapping
    public List<Fund> getFund() {
    	return fundService.getFund();
    }

    @GetMapping("{id}")
    public Fund getFund(@PathVariable("id") Long id) {
        return fundService.getFund(id);
    }

	@PostMapping
    public void addFund(@RequestBody Fund newFund) {
        fundService.addNewFund(newFund);
    }

    @DeleteMapping("{id}")
    public void deleteFund(@PathVariable("id") Long id) {
        fundService.deleteFund(id);
    }

    @PutMapping("{id}")
    public void updateFund(@PathVariable("id") Long id, @RequestBody Fund updatedFund) {
        fundService.updateFund(id, updatedFund);
    }
}


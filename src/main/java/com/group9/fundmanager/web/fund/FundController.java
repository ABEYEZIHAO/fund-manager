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

	@PostMapping("{fund_name}/{manager_id}/{position_id}")
    public void addFund(@PathVariable("fund_name") String fundName, @PathVariable("manager_id") Long mId, @PathVariable("position_id") Long pId) {
        fundService.addNewFund(fundName, mId, pId);
    }

    @DeleteMapping("{id}")
    public void deleteFund(@PathVariable("id") Long id) {
        fundService.deleteFund(id);
    }

    @PutMapping("{id}/{fund_name}/{manager_id}")
    public void updateFund(@PathVariable("id") Long id, @PathVariable("fund_name") String fundName, @PathVariable("manager_id") Long mId) throws Exception {
        fundService.updateFund(id, fundName, mId);
    }
}


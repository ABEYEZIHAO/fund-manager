package com.group9.fundmanager.web.fund;
import com.group9.fundmanager.pojo.Fund;
import com.group9.fundmanager.service.fund.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author dennis
 */
@Controller
public class FundsController {
    private final FundService fundService;

    @Autowired
    public FundsController(FundService fundService) {
        this.fundService = fundService;
    }

    @GetMapping("/funds")
    public String listFund(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
    	return fundService.listFund(m, start, size);
    }

	@PostMapping("/funds")
    public String addFund(Fund newFund) throws Exception {
    	fundService.addNewFund(newFund);
    	return "redirect:funds";
    }

    @DeleteMapping("/funds/{id}")
    public String deleteFund(@PathVariable("id") Long id) throws Exception {
        fundService.deleteFund(id);
    	return "redirect:funds";
    }

    @PutMapping("/funds/{id}")
    public String updateFund(Fund newFund) throws Exception {
        fundService.updateFund(newFund);
    	return "redirect:funds";
    }

    @GetMapping("/funds/{id}")
    public String getFund(@PathVariable("id") Long id,Model m) throws Exception {
    	Fund c= fundService.getFund(id);
    	m.addAttribute("c", c);
    	return "editFund";
    }
}


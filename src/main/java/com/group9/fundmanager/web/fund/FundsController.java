package com.group9.fundmanager.web.fund;
import com.group9.fundmanager.exception.EntityNotFoundException;
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
import org.springframework.web.context.request.WebRequest;

/**
 * @author Dennis
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
    public String addFund(WebRequest webRequest) {
        String[] names = webRequest.getParameterValues("name");
        String[] managerIds = webRequest.getParameterValues("manager_id");
        String[] positionIds = webRequest.getParameterValues("position_id");

        if (names == null) {
            throw new IllegalArgumentException("Please input the fund name.");
        } else if (managerIds == null) {
            throw new IllegalArgumentException("Please input the ID of an existing manager. Otherwise, please create a new manager first.");
        } else if (positionIds == null){
            throw new IllegalArgumentException("Please input the ID of an existing position. Otherwise, please create a new position first.");
        } else {
            fundService.addNewFund(names[0], Long.parseLong(managerIds[0]), Long.parseLong(positionIds[0]));
            return "redirect:funds";
        }
    }

    @DeleteMapping("/funds/{id}")
    public String deleteFund(@PathVariable("id") Long id) {
        fundService.deleteFund(id);
    	return "redirect:/funds";
    }

    @PutMapping("/funds/{id}")
    public String updateFund(@PathVariable("id") Long id, WebRequest webRequest) throws Exception {
        String[] names = webRequest.getParameterValues("name");
        String[] managerIds = webRequest.getParameterValues("manager_id");

        if (names == null) {
            throw new IllegalArgumentException("Please input the fund name.");
        } else if (managerIds == null) {
            throw new IllegalArgumentException("Please input the ID of an existing manager. Otherwise, please create a new manager first.");
        } else {
            fundService.updateFund(id, names[0], Long.parseLong(managerIds[0]));
            return "redirect:/funds";
        }
    }

    @GetMapping("/funds/{id}")
    public String getFund(@PathVariable("id") Long id, Model m) {
    	Fund c= fundService.getFund(id);
    	m.addAttribute("c", c);
    	return "editFund";
    }
}


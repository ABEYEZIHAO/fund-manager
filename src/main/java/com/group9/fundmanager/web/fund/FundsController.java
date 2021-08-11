package com.group9.fundmanager.web.fund;
import com.group9.fundmanager.dao.fund.FundDao;
import com.group9.fundmanager.pojo.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FundsController {
	@Autowired
    FundDao fundDAO;
	
    @GetMapping("/funds")
    public String listFund(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
    	start = start<0?0:start;
//		List<Fund> fund = FundDao.findAll(Sort.by(Sort.Direction.DESC, "id");
    	Sort sort = Sort.by(Sort.Direction.ASC, "id");
    	Pageable pageable = PageRequest.of(start, size, sort);
    	Page<Fund> page =fundDAO.findAll(pageable);
    	m.addAttribute("page", page);
        return "funds";
    }

	@PostMapping("/funds")
    public String addFund(Fund c) throws Exception {
    	fundDAO.save(c);
    	return "redirect:/funds";
    }
    @DeleteMapping("/funds/{id}")
    public String deleteFund(Fund c) throws Exception {
    	fundDAO.delete(c);
    	return "redirect:/funds";
    }
    @PutMapping("/funds/{id}")
    public String updateFund(Fund c) throws Exception {
    	fundDAO.save(c);
    	return "redirect:/funds";
    }
    @GetMapping("/funds/{id}")
    public String getFund(@PathVariable("id") Long id,Model m) throws Exception {
    	Fund c= fundDAO.getOne(id);
    	m.addAttribute("c", c);
    	return "editFund";
    }
}


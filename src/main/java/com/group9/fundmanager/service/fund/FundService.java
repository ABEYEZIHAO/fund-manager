package com.group9.fundmanager.service.fund;

import com.group9.fundmanager.dao.fund.FundDao;
import com.group9.fundmanager.pojo.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * @author abe
 */
@Service
public class FundService {
    private final FundDao fundDao;

    @Autowired
    public FundService(FundDao fundDao) {
        this.fundDao = fundDao;
    }

    /**
     * Get all funds
     * @return all funds
     */
    public List<Fund> getFund() {
        return fundDao.findAll();
    }

    /**
     * Find the fund with the specific ID
     * @param id the target fund ID
     * @return the fund with the specific ID
     */
    public Fund getFund(Long id) {
        Optional<Fund> fund = fundDao.findById(id);
        if (fund.isPresent()) {
            return fund.get();
        } else {
            throw new com.groupnine.fundmanager.exception.FundNotFoundException(id);
        }
    }

    /**
     * Add a new fund
     * @param newFund a Fund object
     */
    public void addNewFund(Fund newFund) {
        Optional<Fund> existingUser = fundDao.findFundByName(newFund.getName());
        if(existingUser.isPresent()){
            throw new com.groupnine.fundmanager.exception.FundNameAlreadyInUseException(newFund.getName());
        }
        fundDao.save(newFund);
    }

    /**
     * Delete a existing fund
     * @param id of the fund user wanna delete
     */
    public void deleteFund(Long id) {
        if(fundDao.existsById(id)) {
            fundDao.deleteById(id);
        }
        else{
            throw new com.groupnine.fundmanager.exception.FundNotFoundException(id);
        }
    }

    public void updateFund(Fund newFund) throws Exception {
        fundDao.save(newFund);
    }

    public String listFund(Model m, int start, int size) throws Exception {
        start = start<0?0:start;
//		List<Fund> fund = FundDao.findAll(Sort.by(Sort.Direction.DESC, "id");
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Fund> page =fundDao.findAll(pageable);
        m.addAttribute("page", page);
        return "funds";
    }
}

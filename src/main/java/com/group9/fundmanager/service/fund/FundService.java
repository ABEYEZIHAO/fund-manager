package com.group9.fundmanager.service.fund;

import com.group9.fundmanager.dao.fund.FundDao;
import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.dao.position.PositionDao;
import com.group9.fundmanager.exception.EntityNotFoundException;
import com.group9.fundmanager.pojo.Fund;
import com.group9.fundmanager.exception.NameAlreadyInUseException;
import com.group9.fundmanager.pojo.Position;
import com.group9.fundmanager.tool.ListTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author abe
 */
@Service
public class FundService {
    private final FundDao fundDao;
    private final ManagerDao managerDao;
    private final PositionDao positionDao;

    @Autowired
    public FundService(FundDao fundDao, ManagerDao managerDao, PositionDao positionDao) {
        this.fundDao = fundDao;
        this.managerDao = managerDao;
        this.positionDao = positionDao;
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
            throw new EntityNotFoundException(id);
        }
    }

    /**
     * Add a new fund
     * @param name name of the fund
     * @param managerId ID of the manager
     */
    public void addNewFund(String name, Long managerId, Long positionId) throws IOException, ClassNotFoundException {
        Optional<Fund> existingFund = fundDao.findFundByName(name);

        if (positionDao.existsById(positionId)) {
            if(existingFund.isPresent()){
                // If the user exists, add the position to this fund
                existingFund.get().getPositions().add(positionDao.getById(positionId));
//            throw new NameAlreadyInUseException(name);
            } else {
                Fund newFund = new Fund(name,
                        managerDao.getById(managerId),
                        com.sun.tools.javac.util.List.of(positionDao.getById(positionId)));
                fundDao.save(newFund);
            }
        } else {
            throw new IllegalArgumentException("Position " + String.valueOf(positionId) + "  not found.");
        }

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
            throw new EntityNotFoundException(id);
        }
    }

    public void updateFund(Long id, String name, Long managerId) throws Exception {
        Optional<Fund> originalFund = fundDao.findById(id);
        if (originalFund.isPresent()) {
            fundDao.save(new Fund(id, name, managerDao.getById(managerId), ListTool.deepCopy(originalFund.get().getPositions())));
        }
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

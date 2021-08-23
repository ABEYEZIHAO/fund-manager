package com.group9.fundmanager.service.fund;

import com.group9.fundmanager.dao.fund.FundDao;
import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.dao.position.PositionDao;
import com.group9.fundmanager.exception.EntityNotFoundException;
import com.group9.fundmanager.exception.NameAlreadyInUseException;
import com.group9.fundmanager.pojo.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Abe
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
            throw new EntityNotFoundException(id, "fund");
        }
    }

    /**
     * Add a new fund
     * @param newFund the new fund object
     */
    public void addNewFund(Fund newFund) {
        Optional<Fund> existingFund = fundDao.findFundByName(newFund.getName());

        if (existingFund.isPresent()) {
            throw new NameAlreadyInUseException("Fund", newFund.getName());
        } else {
            fundDao.save(newFund);
        }
//        if (positionDao.existsById(positionId)) {
//            if(existingFund.isPresent()){
//                // If the user exists, add the position to this fund
//                existingFund.get().getPositions().add(positionDao.getById(positionId));
//            } else {
//                Fund newFund = new Fund(name,
//                        managerDao.getById(managerId),
//                        List.of(positionDao.getById(positionId)));
//                fundDao.save(newFund);
//            }
//        } else {
//            throw new IllegalArgumentException("Position " + positionId + "  not found.");
//        }
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
            throw new EntityNotFoundException(id, "Fund");
        }
    }

    /**
     * Update the fund information
     * @param id ID specifies which fund we wanna modify
     * @param updatedFund the fund with updated information
     */
    @Transactional(rollbackOn = Exception.class)
    public void updateFund(Long id, Fund updatedFund) {
        Optional<Fund> originalFund = fundDao.findById(id);
        if (originalFund.isPresent()) {
            if (!id.equals(originalFund.get().getId())) {
                throw new IllegalStateException("Fund ID in path and in request body are different.");
            }
            fundDao.save(updatedFund);
        } else {
            throw new EntityNotFoundException(id, "Fund");
        }
    }
}

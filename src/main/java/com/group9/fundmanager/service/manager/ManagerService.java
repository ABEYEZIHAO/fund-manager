package com.group9.fundmanager.service.manager;

import com.group9.fundmanager.dao.fund.FundDao;
import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.pojo.Fund;
import com.group9.fundmanager.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

/**
 * @author abe
 */
@Service
public class ManagerService {
    private final ManagerDao managerDao;

    @Autowired
    public ManagerService(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    /**
     * Get all managers
     * @return all managers
     */
    public List<Manager> getManager() {
        return managerDao.findAll();
    }

    /**
     * Find the manager with the specific ID
     * @param id the target manager ID
     * @return the manager with the specific ID
     */
    public Manager getManager(Long id) {
        Optional<Manager> manager = managerDao.findById(id);
        if (manager.isPresent()) {
            return manager.get();
        } else {
            throw new com.groupnine.fundmanager.exception.FundNotFoundException(id);
        }
    }

    /**
     * Add a new manager
     * @param newManager a Manager object
     */
    public void addNewManager(Manager newManager) {
        Optional<Manager> existingManage = managerDao.findManagerByFullName(newManager.getManagerName());
        if(existingManage.isPresent()){
            throw new com.groupnine.fundmanager.exception.FundNameAlreadyInUseException(newManager.getManagerName());
        }
        managerDao.save(newManager);
    }

    /**
     * Delete a existing manager
     * @param id of the manager user wanna delete
     */
    public void deleteManager(Long id) {
        if(managerDao.existsById(id)) {
            managerDao.deleteById(id);
        }
        else{
            throw new com.groupnine.fundmanager.exception.FundNotFoundException(id);
        }
    }

    public void updateManager(Manager newManager) throws Exception {
        managerDao.save(newManager);
    }

    public String listManager(Model m, int start, int size) throws Exception {
        start = start<0?0:start;
//		List<Manager> manager = ManageDao.findAll(Sort.by(Sort.Direction.DESC, "id");
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Manager> page =managerDao.findAll(pageable);
        m.addAttribute("page", page);
        return "managers";
    }
}

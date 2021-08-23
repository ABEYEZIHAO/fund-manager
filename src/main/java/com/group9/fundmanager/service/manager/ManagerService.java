package com.group9.fundmanager.service.manager;

import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.exception.NameAlreadyInUseException;
import com.group9.fundmanager.exception.EntityNotFoundException;
import com.group9.fundmanager.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Dennis
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
            throw new EntityNotFoundException(id, "manager");
        }
    }

    /**
     * Add a new manager
     * @param newManager the new manager object
     */
    public void addNewManager(Manager newManager) {
        Optional<Manager> existingManage = managerDao.findManagerByFullName(newManager.getFullName());
        if(existingManage.isPresent()){
            throw new NameAlreadyInUseException("Manager", newManager.getFullName());
        } else {
            managerDao.save(newManager);
        }
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
            throw new EntityNotFoundException(id, "manager");
        }
    }

    /**
     * Update the manager information
     * @param id ID specifies which manager we wanna modify
     * @param updatedManager the manager object with the updated information
     */
    @Transactional(rollbackOn = Exception.class)
    public void updateManager(Long id, Manager updatedManager) {
        Optional<Manager> originalManager = managerDao.findById(id);
        if (originalManager.isPresent()) {
            if (!id.equals(originalManager.get().getId())) {
                throw new IllegalStateException("Manager ID in path and in request body are different.");
            }
            managerDao.save(updatedManager);
        } else {
            throw new EntityNotFoundException(id, "Manager");
        }
    }
}

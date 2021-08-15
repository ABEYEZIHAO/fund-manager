package com.group9.fundmanager.service.manager;

import com.group9.fundmanager.dao.manager.ManagerDao;
import com.group9.fundmanager.exception.NameAlreadyInUseException;
import com.group9.fundmanager.exception.EntityNotFoundException;
import com.group9.fundmanager.pojo.Fund;
import com.group9.fundmanager.pojo.Manager;
import com.group9.fundmanager.tool.ListTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
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
     * @param firstName the first name of the new manager
     * @param lastName the last name of the new manager
     */
    public void addNewManager(String firstName, String lastName) {
        Optional<Manager> existingManage = managerDao.findManagerByFullName(firstName + ' ' + lastName);
        if(existingManage.isPresent()){
            throw new NameAlreadyInUseException("Manager", firstName +' ' + lastName);
        } else {
            Manager newManager = new Manager(firstName, lastName, new ArrayList<Fund>());
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
     * @param firstName Target first name of the manager
     * @param lastName Target last name of the manager
     * @throws Exception Capture some potential exceptions caused by ListTool.deepCopy
     */
    public void updateManager(Long id, String firstName, String lastName) throws Exception {
        Optional<Manager> originalManager = managerDao.findById(id);
        if (originalManager.isPresent()) {
            managerDao.save(new Manager(id, firstName, lastName, ListTool.deepCopy(originalManager.get().getFunds())));
        }
    }

    /**
     * Implement the PAGE function
     * @param m model
     * @param start start page
     * @param size page size
     * @return managers.jsp
     */
    public String listManager(Model m, int start, int size) {
        start = start<0?0:start;
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Manager> page =managerDao.findAll(pageable);
        m.addAttribute("page", page);
        return "managers";
    }
}

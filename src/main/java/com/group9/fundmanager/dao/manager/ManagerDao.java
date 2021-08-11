package com.group9.fundmanager.dao.manager;

import com.group9.fundmanager.pojo.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author abe
 */
public interface ManagerDao extends JpaRepository<Manager, Long>{
    /**
     * Find a manager with the target name
     * @param name name of a manager
     * @return the manager with the specific name
     */
    Optional<Manager> findManagerByFullName(String name);
}

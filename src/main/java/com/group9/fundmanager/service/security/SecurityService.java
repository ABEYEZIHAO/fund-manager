package com.group9.fundmanager.service.security;

import com.group9.fundmanager.dao.security.SecurityDao;
import com.group9.fundmanager.exception.NameAlreadyInUseException;
import com.group9.fundmanager.exception.EntityNotFoundException;
import com.group9.fundmanager.pojo.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Abe
 */
@Service
public class SecurityService {
    private final SecurityDao securityDao;

    @Autowired
    public SecurityService(SecurityDao securityDao) {
        this.securityDao = securityDao;
    }

    /**
     * Get all securities
     * @return all securities
     */
    public List<Security> getSecurity() {
        return securityDao.findAll();
    }

    /**
     * Find the security with the specific ID
     * @param id the target security ID
     * @return the security with the specific ID
     */
    public Security getSecurity(Long id) {
        Optional<Security> security = securityDao.findById(id);
        if (security.isPresent()) {
            return security.get();
        } else {
            throw new EntityNotFoundException(id, "security");
        }
    }

    /**
     * Add a new security
     * @param newSecurity a Security object
     */
    public void addNewSecurity(Security newSecurity) {
        Optional<Security> existingSecurity = securityDao.findSecurityBySymbol(newSecurity.getSymbol());
        if(existingSecurity.isPresent()){
            throw new NameAlreadyInUseException("Security", newSecurity.getSymbol());
        }
        securityDao.save(newSecurity);
    }

    /**
     * Delete a existing security
     * @param id of the security user wanna delete
     */
    public void deleteSecurity(Long id) {
        if(securityDao.existsById(id)) {
            securityDao.deleteById(id);
        }
        else{
            throw new EntityNotFoundException(id, "security");
        }
    }

    /**
     * Update the security information
     * @param id ID of the security we wanna modify
     * @param updatedSecurity a new security including the modified information
     */
    @Transactional(rollbackOn = Exception.class)
    public void updateSecurity(Long id, Security updatedSecurity) {
        Optional<Security> originalSecurity = securityDao.findById(id);
        if (originalSecurity.isPresent()) {
            if (!id.equals(originalSecurity.get().getId())) {
                throw new IllegalStateException("Security ID in path and in request body are different.");
            }
            securityDao.save(updatedSecurity);
        } else {
            throw new EntityNotFoundException(id, "Security");
        }
    }
}

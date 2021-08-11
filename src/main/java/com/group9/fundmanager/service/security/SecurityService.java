package com.group9.fundmanager.service.security;

import com.group9.fundmanager.dao.security.SecurityDao;
import com.group9.fundmanager.exception.NameAlreadyInUseException;
import com.group9.fundmanager.exception.EntityNotFoundException;
import com.group9.fundmanager.pojo.Security;
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
            throw new EntityNotFoundException(id);
        }
    }

    /**
     * Add a new security
     * @param newSecurity a Security object
     */
    public void addNewSecurity(Security newSecurity) {
        Optional<Security> existingSecurity = securityDao.findSecurityBySymbol(newSecurity.getSymbol());
        if(existingSecurity.isPresent()){
            throw new NameAlreadyInUseException(newSecurity.getSymbol());
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
            throw new EntityNotFoundException(id);
        }
    }

    public void updateSecurity(Security newSecurity) throws Exception {
        securityDao.save(newSecurity);
    }

    public String listSecurity(Model m, int start, int size) throws Exception {
        start = start<0?0:start;
//		List<Security> security = SecurityDao.findAll(Sort.by(Sort.Direction.DESC, "id");
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Security> page = securityDao.findAll(pageable);
        m.addAttribute("page", page);
        return "securities";
    }
}

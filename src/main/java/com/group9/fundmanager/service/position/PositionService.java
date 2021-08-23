package com.group9.fundmanager.service.position;

import com.group9.fundmanager.dao.position.PositionDao;
import com.group9.fundmanager.dao.security.SecurityDao;
import com.group9.fundmanager.exception.EntityNotFoundException;
import com.group9.fundmanager.pojo.Position;
import com.group9.fundmanager.pojo.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Dennis
 */
@Service
public class PositionService {
    private final PositionDao positionDao;
    private final SecurityDao securityDao;

    @Autowired
    public PositionService(PositionDao positionDao, SecurityDao securityDao) {
        this.positionDao = positionDao;
        this.securityDao = securityDao;
    }

    /**
     * Get all positions
     * @return all positions
     */
    public List<Position> getPosition() {
        return positionDao.findAll();
    }

    /**
     * Find the position with the specific ID
     * @param id the target position ID
     * @return the position with the position ID
     */
    public Position getPosition(Long id) {
        Optional<Position> position = positionDao.findById(id);
        if (position.isPresent()) {
            return position.get();
        } else {
            throw new EntityNotFoundException(id, "position");
        }
    }

    /**
     * Add a new position
     * @param symbol symbol of the security
     * @param quantity quantity of the security
     */
    public void addNewPosition(String symbol, int quantity) {
        Optional<Security> existingSecurity = securityDao.findSecurityBySymbol(symbol);
        if (existingSecurity.isPresent()) {
            Position newPosition = new Position(existingSecurity.get(), quantity, LocalDate.now(), null);
            positionDao.save(newPosition);
        } else {
            throw new IllegalArgumentException("The security with name " + symbol + " not found.");
        }

    }

    /**
     * Delete a existing position
     * @param id of the position user wanna delete
     */
    public void deletePosition(Long id) {
        if(positionDao.existsById(id)) {
            positionDao.deleteById(id);
        }
        else{
            throw new EntityNotFoundException(id, "position");
        }
    }

    /**
     * Update the position information
     * @param newPosition a new position including the modified information
     */
    public void updatePosition(Position newPosition) {
        positionDao.save(newPosition);
    }
}

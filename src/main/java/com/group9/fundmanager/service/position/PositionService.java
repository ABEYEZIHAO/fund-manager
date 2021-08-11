package com.group9.fundmanager.service.position;

import com.group9.fundmanager.dao.position.PositionDao;
import com.group9.fundmanager.exception.EntityNotFoundException;
import com.group9.fundmanager.pojo.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

/*

 */
@Service
public class PositionService {
    private final PositionDao positionDao;

    @Autowired
    public PositionService(PositionDao positionDao) {
        this.positionDao = positionDao;
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
            throw new EntityNotFoundException(id);
        }
    }

    /**
     * Add a new position
     * @param newPosition position object
     */
    public void addNewPosition(Position newPosition) {
        Optional<Position> existingUser = positionDao.findPositionByPositionId(newPosition.getPositionId());
//        if(existingUser.isPresent()){
//            throw new com.group9.fundmanager.exception.FundNameAlreadyInUseException(newPosition.getPositionId());
//        }
        positionDao.save(newPosition);
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
            throw new EntityNotFoundException(id);
        }
    }

    public void updatePosition(Position newPosition) throws Exception {
        positionDao.save(newPosition);
    }

    public String listPosition(Model m, int start, int size) throws Exception {
        start = start<0?0:start;
//		List<Fund> fund = FundDao.findAll(Sort.by(Sort.Direction.DESC, "id");
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Position> page =positionDao.findAll(pageable);
        m.addAttribute("page", page);
        return "funds";
    }
}

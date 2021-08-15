package com.group9.fundmanager.dao.position;

import com.group9.fundmanager.pojo.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Dennis
 */
public interface PositionDao extends JpaRepository<Position,Long> {
    /**
     * Find a position having the specific ID
     * @param id ID of the target Position
     * @return a Position entity which has the specific ID
     */
    Optional<Position> findPositionById(Long id);

}

package com.group9.fundmanager.dao.position;

import com.group9.fundmanager.pojo.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionDao extends JpaRepository<Position,Long> {
    Optional<Position> findPositionById(Long id);

}

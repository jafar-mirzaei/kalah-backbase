package com.backbase.kalah.kalahassignment.persistance.repository;

import com.backbase.kalah.kalahassignment.persistance.model.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jafar Mirzaei (mirzaie@caspco.ir)
 * @version 1.0 2019.1130
 * @since 1.0
 */
public interface GameRepository extends JpaRepository<GameEntity,Long> {
}


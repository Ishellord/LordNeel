package com.id.dragneel.LatihanCRUD.repo;

import com.id.dragneel.LatihanCRUD.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {
}

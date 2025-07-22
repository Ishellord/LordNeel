package com.id.dragneel.LatihanCRUD.repo;

import com.id.dragneel.LatihanCRUD.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepo extends JpaRepository<Platform, Long> {
}

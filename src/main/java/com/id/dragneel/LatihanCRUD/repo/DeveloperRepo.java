package com.id.dragneel.LatihanCRUD.repo;

import com.id.dragneel.LatihanCRUD.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepo extends JpaRepository<Developer, Long> {

    boolean existsByName(String Name);
}

package com.id.dragneel.LatihanCRUD.repo;

import com.id.dragneel.LatihanCRUD.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepo extends JpaRepository<Rating, Long> {
}

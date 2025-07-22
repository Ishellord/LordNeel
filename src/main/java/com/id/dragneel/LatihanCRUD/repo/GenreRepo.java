package com.id.dragneel.LatihanCRUD.repo;

import com.id.dragneel.LatihanCRUD.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepo extends JpaRepository<Genre, Long> {

}

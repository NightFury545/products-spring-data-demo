package org.nightfury.repository;

import java.util.Optional;
import org.nightfury.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findById(long id);
    Optional<Category> findByTitle(String title);
}

package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.FoodCategory;

import java.util.Optional;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
    Optional<FoodCategory> findById(Long category);
}
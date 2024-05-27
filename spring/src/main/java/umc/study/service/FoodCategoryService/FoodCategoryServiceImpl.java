package umc.study.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.repository.FoodCategoryRepository;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService{

    private final FoodCategoryRepository foodCategoryRepository;

    public boolean existsById(Long id) {
        return foodCategoryRepository.existsById(id);
    }
}
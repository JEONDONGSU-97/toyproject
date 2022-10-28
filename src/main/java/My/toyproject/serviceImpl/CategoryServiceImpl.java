package My.toyproject.serviceImpl;

import My.toyproject.domain.Category;
import My.toyproject.repository.CategoryRepository;
import My.toyproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Long saveCategory(Category category) {
        categoryRepository.save(category);
        return category.getId();
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}

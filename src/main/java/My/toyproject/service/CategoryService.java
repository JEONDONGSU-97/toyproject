package My.toyproject.service;

import My.toyproject.domain.Category;

import java.util.List;

public interface CategoryService {

    //카테고리 저장
    public Long saveCategory(Category category);

    //카테고리 조회
    public Category findById(Long categoryId);

    //모든 카테고리 조회
    public List<Category> findAll();

}

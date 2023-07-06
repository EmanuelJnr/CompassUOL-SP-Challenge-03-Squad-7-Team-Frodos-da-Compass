package br.com.compassuol.pb.challenge.products.service;

import br.com.compassuol.pb.challenge.products.payload.CategoryDTO;
import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO CategoryDTO);

    List<CategoryDTO> getAllCategory();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO updateCategory(CategoryDTO CategoryDTO, Long id);

    void deleteCategoryById(Long id);
}

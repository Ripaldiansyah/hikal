package ac.id.unindra.hikal_spk.utils.service;

import java.util.List;

import ac.id.unindra.hikal_spk.utils.model.CategoryModel;

public interface CategoryService {
    boolean isRegistered(CategoryModel model);

    void createCategory(CategoryModel model);

    void updateCategory(CategoryModel model);

    void deleteCategory(CategoryModel model);

    List<CategoryModel> getCategory();

    List<CategoryModel> searchCategory(String key);
}

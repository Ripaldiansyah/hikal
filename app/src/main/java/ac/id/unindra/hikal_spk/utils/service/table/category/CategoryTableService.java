package ac.id.unindra.hikal_spk.utils.service.table.category;

import ac.id.unindra.hikal_spk.utils.model.category.CategoryModel;

import java.util.List;

public interface CategoryTableService {

    void setData(List<CategoryModel> user);

    void removeData(int index);

    void clear();

    CategoryModel getSelectedIndex(int index);
}

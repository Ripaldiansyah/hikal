package ac.id.unindra.hikal_spk.utils.service.tableService;

import java.util.List;

import ac.id.unindra.hikal_spk.utils.model.CategoryModel;

public interface CategoryTableService {

    void setData(List<CategoryModel> category);

    void removeData(int index);

    void clear();

    CategoryModel getSelectedIndex(int index);
}

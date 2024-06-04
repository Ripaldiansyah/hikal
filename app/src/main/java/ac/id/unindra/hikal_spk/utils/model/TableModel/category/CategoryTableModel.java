package ac.id.unindra.hikal_spk.utils.model.TableModel.category;

import java.util.List;
import java.util.ArrayList;
import ac.id.unindra.hikal_spk.category.controller.CategoryController;
import ac.id.unindra.hikal_spk.utils.model.category.CategoryModel;
import ac.id.unindra.hikal_spk.utils.service.table.category.CategoryTableService;
import javax.swing.table.AbstractTableModel;

public class CategoryTableModel extends AbstractTableModel implements CategoryTableService {

    @Override
    public int getRowCount() {
        return category.size();
    }

    @Override
    public int getColumnCount() {
        return controller.tableHeader().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return category.get(rowIndex).getCategoryID();
            case 1:
                return category.get(rowIndex).getCategoryName();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return controller.tableHeader()[column];
    }

    @Override
    public void setData(List<CategoryModel> user) {
        clear();
        this.category.addAll(user);
        fireTableDataChanged();
    }

    @Override
    public void removeData(int index) {
        category.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public void clear() {
        category.clear();
        fireTableDataChanged();
    }

    @Override
    public CategoryModel getSelectedIndex(int index) {
        return category.get(index);
    }

    CategoryController controller = new CategoryController();
    List<CategoryModel> category = new ArrayList<>();

}

package ac.id.unindra.hikal_spk.utils.model.TableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ac.id.unindra.hikal_spk.criteria.controller.CriteriaController;
import ac.id.unindra.hikal_spk.utils.model.CriteriaModel;
import ac.id.unindra.hikal_spk.utils.service.tableService.CriteriaTableService;

public class CriteriaTableModel extends AbstractTableModel implements CriteriaTableService {

    @Override
    public int getRowCount() {
        return criteria.size();
    }

    @Override
    public int getColumnCount() {
        return controller.tableHeader().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return criteria.get(rowIndex).getCriteraID();
            case 1:
                return criteria.get(rowIndex).getCriteriaName();
            case 2:
                return criteria.get(rowIndex).getCriteriaWeight();
            case 3:
                return criteria.get(rowIndex).getCriteriaType();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return controller.tableHeader()[column];
    }

    @Override
    public void setData(List<CriteriaModel> criteria) {
        clear();
        this.criteria.addAll(criteria);
        fireTableDataChanged();
    }

    @Override
    public void removeData(int index) {
        criteria.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public void clear() {
        criteria.clear();
        fireTableDataChanged();
    }

    @Override
    public CriteriaModel getSelectedIndex(int index) {
        return criteria.get(index);
    }

    CriteriaController controller = new CriteriaController();
    List<CriteriaModel> criteria = new ArrayList<>();

}

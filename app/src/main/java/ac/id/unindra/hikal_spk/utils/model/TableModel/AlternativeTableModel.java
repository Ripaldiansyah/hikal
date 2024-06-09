package ac.id.unindra.hikal_spk.utils.model.TableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ac.id.unindra.hikal_spk.alternative.controller.AlternativeController;
import ac.id.unindra.hikal_spk.utils.model.AlternativeModel;
import ac.id.unindra.hikal_spk.utils.service.tableService.AlternativeTableService;

public class AlternativeTableModel extends AbstractTableModel implements AlternativeTableService {

    @Override
    public int getRowCount() {
        return alternative.size();
    }

    @Override
    public int getColumnCount() {
        return controller.tableHeader().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return alternative.get(rowIndex).getAlternativeID();
            case 1:
                return alternative.get(rowIndex).getCategory().getCategoryName();
            case 2:
                return alternative.get(rowIndex).getAlternativeName();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return controller.tableHeader()[column];
    }

    @Override
    public void setData(List<AlternativeModel> alternative) {
        clear();
        this.alternative.addAll(alternative);
        fireTableDataChanged();
    }

    @Override
    public void removeData(int index) {
        alternative.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public void clear() {
        alternative.clear();
        fireTableDataChanged();
    }

    @Override
    public AlternativeModel getSelectedIndex(int index) {
        return alternative.get(index);
    }

    AlternativeController controller = new AlternativeController();
    List<AlternativeModel> alternative = new ArrayList<>();

}

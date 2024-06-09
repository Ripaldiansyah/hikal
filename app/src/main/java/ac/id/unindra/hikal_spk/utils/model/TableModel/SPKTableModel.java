package ac.id.unindra.hikal_spk.utils.model.TableModel;

import ac.id.unindra.hikal_spk.spk.controller.SPKController;
import ac.id.unindra.hikal_spk.utils.model.SPKModel;
import ac.id.unindra.hikal_spk.utils.service.tableService.SPKTableService;

import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class SPKTableModel extends AbstractTableModel implements SPKTableService {

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
                return alternative.get(rowIndex).getSpkID();
            case 1:
                return alternative.get(rowIndex).getCategory().getCategoryName();
            case 2:
                return alternative.get(rowIndex).getCreatedAt();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return controller.tableHeader()[column];
    }

    @Override
    public void setData(List<SPKModel> alternative) {
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
    public SPKModel getSelectedIndex(int index) {
        return alternative.get(index);
    }

    SPKController controller = new SPKController();
    List<SPKModel> alternative = new ArrayList<>();

}

package ac.id.unindra.hikal_spk.utils.model.TableModel.alternative;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ac.id.unindra.hikal_spk.alternative.controller.AlternativeController;
import ac.id.unindra.hikal_spk.user.controller.UserController;
import ac.id.unindra.hikal_spk.utils.model.alternative.AlternativeModel;
import ac.id.unindra.hikal_spk.utils.model.user.UserModel;
import ac.id.unindra.hikal_spk.utils.service.table.alternative.AlternativeTableService;

public class AlternativeTableModel extends AbstractTableModel implements AlternativeTableService {

    @Override
    public int getRowCount() {
        return user.size();
    }

    @Override
    public int getColumnCount() {
        return controller.tableHeader().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return user.get(rowIndex).getAlternativeID();
            case 1:
                return user.get(rowIndex).getCategory().getCategoryName();
            case 2:
                return user.get(rowIndex).getAlternativeName();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return controller.tableHeader()[column];
    }

    @Override
    public void setData(List<AlternativeModel> user) {
        clear();
        this.user.addAll(user);
        fireTableDataChanged();
    }

    @Override
    public void removeData(int index) {
        user.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public void clear() {
        user.clear();
        fireTableDataChanged();
    }

    @Override
    public AlternativeModel getSelectedIndex(int index) {
        return user.get(index);
    }

    AlternativeController controller = new AlternativeController();
    List<AlternativeModel> user = new ArrayList<>();

}

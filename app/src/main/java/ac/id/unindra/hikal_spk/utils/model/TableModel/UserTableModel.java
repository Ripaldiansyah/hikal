package ac.id.unindra.hikal_spk.utils.model.TableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ac.id.unindra.hikal_spk.user.controller.UserController;
import ac.id.unindra.hikal_spk.utils.model.UserModel;
import ac.id.unindra.hikal_spk.utils.service.tableService.UserTableService;

public class UserTableModel extends AbstractTableModel implements UserTableService {

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
                return user.get(rowIndex).getUserID();
            case 1:
                return user.get(rowIndex).getFullname();
            case 2:
                return user.get(rowIndex).getUsername();
            case 3:
                return user.get(rowIndex).getGender();
            case 4:
                return user.get(rowIndex).getRole();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return controller.tableHeader()[column];
    }

    @Override
    public void setData(List<UserModel> user) {
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
    public UserModel getSelectedIndex(int index) {
        return user.get(index);
    }

    UserController controller = new UserController();
    List<UserModel> user = new ArrayList<>();

}

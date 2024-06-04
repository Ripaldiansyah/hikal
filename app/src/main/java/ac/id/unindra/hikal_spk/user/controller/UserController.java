package ac.id.unindra.hikal_spk.user.controller;

import java.util.List;
import javax.swing.JOptionPane;
import ac.id.unindra.hikal_spk.utils.dao.user.UserDAO;
import ac.id.unindra.hikal_spk.utils.model.TableModel.user.UserTableModel;
import ac.id.unindra.hikal_spk.utils.model.user.UserModel;

public class UserController {

    public UserController() {
        this.DAO = new UserDAO();
    }

    public String[] tableHeader() {
        return model.getColumnHeader();
    }

    public List<UserModel> getData() {
        return DAO.getUser();
    }

    public List<UserModel> searchData(String key) {
        return DAO.searchUser(key);
    }

    public void deleteData(UserTableModel tableModel, int indexRow) {
        int response = JOptionPane.showConfirmDialog(
                null,
                "Apakah Anda yakin ingin menghapus akun ?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            model.setUserID(tableModel.getSelectedIndex(indexRow).getUserID());
            DAO.deleteUser(model);
            tableModel.removeData(indexRow);
        }

    }

    private UserDAO DAO;
    private UserModel model = new UserModel();

}

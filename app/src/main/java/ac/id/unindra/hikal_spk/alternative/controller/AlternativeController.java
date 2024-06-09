package ac.id.unindra.hikal_spk.alternative.controller;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.utils.dao.AlternativeDAO;
import ac.id.unindra.hikal_spk.utils.model.AlternativeModel;
import ac.id.unindra.hikal_spk.utils.model.CategoryModel;
import ac.id.unindra.hikal_spk.utils.model.TableModel.AlternativeTableModel;

public class AlternativeController {

    public AlternativeController() {
        this.DAO = new AlternativeDAO();
    }

    public String[] tableHeader() {
        return model.getColumnHeader();
    }

    public List<AlternativeModel> getData() {
        return DAO.getAlternative();
    }

    public List<AlternativeModel> searchData(String key) {
        return DAO.searchAlternative(key);
    }

    public boolean isRegistered(AlternativeModel model) {
        DAO.setIDCategory(model);
        return DAO.isRegistered(model);
    }

    public void updateData(AlternativeModel model) {
        DAO.updateAlternative(model);
        notificationUpdate();
    }

    public void createCategory(AlternativeModel model) {
        DAO.createAlternative(model);
        notificationCreate();
    }

    public void deleteData(AlternativeTableModel tableModel, int indexRow) {
        int response = JOptionPane.showConfirmDialog(
                null,
                "Apakah Anda yakin ingin menghapus kategori ?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            model.setAlternativeID(tableModel.getSelectedIndex(indexRow).getAlternativeID());
            DAO.deleteAlternative(model);
            tableModel.removeData(indexRow);
        }
    }

    public List<String> getCategory() {
        return DAO.getCategory();
    }

    public void getInput(AlternativeModel model, TextFieldCustom txtAlternativeName, JComboBox<String> category) {
        CategoryModel categoryModel = new CategoryModel();
        String alternativeName = txtAlternativeName.getText();
        String categoryName = category.getSelectedItem().toString();
        model.setAlternativeName(alternativeName);
        categoryModel.setCategoryName(categoryName);
        model.setCategory(categoryModel);

    }

    public boolean fieldNotEmpty(TextFieldCustom field) {
        if (field.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pastikan semua telah terisi",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;

    }

    public void notificationUpdate() {
        JOptionPane.showMessageDialog(null,
                "Alternatif Berhasil diubah",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void notificationCreate() {
        JOptionPane.showMessageDialog(null,
                "Alternatif Berhasil dibuat",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void notificationIsnAvail() {
        JOptionPane.showMessageDialog(null,

                "Nama Alternatif telah terdaftar pada kategori",
                "Masukan nama Alternatif lain",
                JOptionPane.ERROR_MESSAGE);
    }

    public void notificationIsnChange() {
        JOptionPane.showMessageDialog(null,
                "Tidak ada perubahan",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private AlternativeDAO DAO;
    private AlternativeModel model = new AlternativeModel();

}

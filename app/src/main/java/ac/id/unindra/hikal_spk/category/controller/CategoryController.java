package ac.id.unindra.hikal_spk.category.controller;

import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.utils.dao.category.CategoryDAO;
import ac.id.unindra.hikal_spk.utils.model.TableModel.category.CategoryTableModel;
import ac.id.unindra.hikal_spk.utils.model.category.CategoryModel;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoryController {

    public CategoryController() {
        this.DAO = new CategoryDAO();
    }

    public String[] tableHeader() {
        return model.getColumnHeader();
    }

    public List<CategoryModel> getData() {
        return DAO.getCategory();
    }

    public void updateData(CategoryModel model) {
        DAO.updateCategory(model);
        notificationUpdate();
    }

    public void createCategory(CategoryModel model) {
        DAO.createCategory(model);
        notificationCreate();
    }

    public boolean isRegistered(CategoryModel model) {
        return DAO.isRegistered(model);
    }

    public List<CategoryModel> searchData(String key) {
        return DAO.searchCategory(key);
    }

    public void deleteData(CategoryTableModel tableModel, int indexRow) {
        int response = JOptionPane.showConfirmDialog(
                null,
                "Apakah Anda yakin ingin menghapus kategori ?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            model.setCategoryID(tableModel.getSelectedIndex(indexRow).getCategoryID());
            DAO.deleteCategory(model);
            tableModel.removeData(indexRow);
        }
    }

    public void getInput(CategoryModel model, TextFieldCustom txtCategoryName) {
        String categoryName = txtCategoryName.getText();
        model.setCategoryName(categoryName);
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
                "Kategori Berhasil diubah",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void notificationCreate() {
        JOptionPane.showMessageDialog(null,
                "Kategori Berhasil dibuat",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void notificationIsnAvail() {
        JOptionPane.showMessageDialog(null,
                "Masukan nama kategori lain",
                "Nama kategori telah terdaftar",
                JOptionPane.ERROR_MESSAGE);
    }

    public void notificationIsnChange() {
        JOptionPane.showMessageDialog(null,
                "Tidak ada perubahan",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private CategoryDAO DAO;
    private CategoryModel model = new CategoryModel();

}

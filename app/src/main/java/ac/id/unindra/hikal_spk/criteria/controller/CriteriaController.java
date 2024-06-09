package ac.id.unindra.hikal_spk.criteria.controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.utils.dao.CriteriaDAO;
import ac.id.unindra.hikal_spk.utils.model.CategoryModel;
import ac.id.unindra.hikal_spk.utils.model.CriteriaModel;
import ac.id.unindra.hikal_spk.utils.model.TableModel.CategoryTableModel;
import ac.id.unindra.hikal_spk.utils.model.TableModel.CriteriaTableModel;

public class CriteriaController {

    public CriteriaController() {
        this.DAO = new CriteriaDAO();
    }

    public String[] tableHeader() {
        return model.getColumnHeader();
    }

    public List<CriteriaModel> getData() {
        return DAO.getCriteria();
    }

    public void updateData(CriteriaModel model) {
        DAO.updateCriteria(model);
        notificationUpdate();
    }

    public void createCategory(CriteriaModel model) {
        DAO.createCriteria(model);
        notificationCreate();
    }

    public boolean isRegistered(CriteriaModel model) {
        return DAO.isRegistered(model);
    }

    public List<CriteriaModel> searchData(String key) {
        return DAO.searchCriteria(key);
    }

    public void deleteData(CriteriaTableModel tableModel, int indexRow) {
        int response = JOptionPane.showConfirmDialog(
                null,
                "Apakah Anda yakin ingin menghapus Kriteria ?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            model.setCriteraID(tableModel.getSelectedIndex(indexRow).getCriteraID());
            DAO.deleteCriteria(model);
            tableModel.removeData(indexRow);
        }
    }

    public void getInput(CriteriaModel model, TextFieldCustom txtCriteriaName, TextFieldCustom txtCriteriaWeight,
            JComboBox<String> cbCriteria) {
        try {
            String text = txtCriteriaWeight.getText().trim();
            if (text.isEmpty()) {
                throw new NumberFormatException("Empty string");
            }
            double criteriaWeight = Double.parseDouble(text);
            String categoryName = txtCriteriaName.getText();
            String criteriaType = cbCriteria.getSelectedItem().toString();
            model.setCriteriaName(categoryName);
            model.setCriteriaWeight(criteriaWeight);
            model.setCriteriaType(criteriaType);
        } catch (NumberFormatException e) {

        }

    }

    public boolean fieldNotEmpty(TextFieldCustom txtCriteriaName, TextFieldCustom txtCriteriaWeight) {
        boolean criteriaNameIsEmpty = txtCriteriaName.getText().trim().isEmpty();
        boolean CriteriaWeightIsEmpty = txtCriteriaWeight.getText().trim().isEmpty();
        if (criteriaNameIsEmpty || CriteriaWeightIsEmpty) {
            JOptionPane.showMessageDialog(null, "Pastikan semua telah terisi",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;

    }

    public void notificationUpdate() {
        JOptionPane.showMessageDialog(null,
                "Kriteria Berhasil diubah",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void notificationCreate() {
        JOptionPane.showMessageDialog(null,
                "Kriteria Berhasil dibuat",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void notificationIsnAvail() {
        JOptionPane.showMessageDialog(null,
                "Masukan nama Kriteria lain",
                "Nama Kriteria telah terdaftar",
                JOptionPane.ERROR_MESSAGE);
    }

    private CriteriaDAO DAO;
    private CriteriaModel model = new CriteriaModel();
}

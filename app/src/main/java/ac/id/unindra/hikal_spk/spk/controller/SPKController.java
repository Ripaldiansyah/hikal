package ac.id.unindra.hikal_spk.spk.controller;

import javax.swing.JOptionPane;

import ac.id.unindra.hikal_spk.utils.dao.AlternativeDAO;
import ac.id.unindra.hikal_spk.utils.dao.SPKDAO;
import ac.id.unindra.hikal_spk.utils.model.SPKModel;
import ac.id.unindra.hikal_spk.utils.model.TableModel.SPKTableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SPKController {

    public SPKController() {
        this.DAO = new SPKDAO();
        this.alternativeDAO = new AlternativeDAO();
    }

    public void saveSPK(SPKModel model) {
        DAO.createSPK(model);
    }

    public String[] tableHeader() {
        return model.getColumnHeader();
    }

    public List<SPKModel> getData(String userId) {
        return DAO.getSPK(userId);
    }

    public List<Map<Object, Object>> getRank() {
        return rank;
    }

    public void setRank(List<Map<Object, Object>> rank) {
        this.rank = rank;
    }

    public List<SPKModel> searchData(String key, String userId) {
        return DAO.searchSPK(key, userId);
    }

    public List<String> getAlternative(String categoryID) {
        return DAO.getAlternative(categoryID);
    }

    public List<Map<Object, Object>> getDetailRank(SPKModel spk) {
        return DAO.getDetailRank(spk);
    }

    public void createCategory(SPKModel model) {
        DAO.createSPK(model);
        notificationCreate();
    }

    public void deleteData(SPKTableModel tableModel, int indexRow) {
        int response = JOptionPane.showConfirmDialog(
                null,
                "Apakah Anda yakin ingin menghapus Keputusan ?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            model.setSpkID(tableModel.getSelectedIndex(indexRow).getSpkID());
            DAO.deleteSPK(model);
            tableModel.removeData(indexRow);
        }
    }

    public List<String> getCategory() {
        return alternativeDAO.getCategory();
    }

    public String getCategoryID(String categoryName) {
        return DAO.getCategoryID(categoryName);
    }

    public double getCriteriaWeight(SPKModel model) {
        return DAO.getCriteriaWeight(model);
    }

    public List<String> getCriteria() {
        return DAO.getCriteria();
    }

    public void notificationCreate() {
        JOptionPane.showMessageDialog(null,
                "Keputusan Berhasil disimpan",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void notificationCriteriaNotSelected() {
        JOptionPane.showMessageDialog(null,
                "Pastikan terdapat satu kriteria terpilih ",
                "Tidak ada kriteria terpilih",
                JOptionPane.ERROR_MESSAGE);
    }

    public void notificationAlternativeNotSelected() {
        JOptionPane.showMessageDialog(null,
                "Pastikan terdapat satu alternatif terpilih ",
                "Tidak ada alternatif terpilih",
                JOptionPane.ERROR_MESSAGE);
    }

    private SPKDAO DAO;
    private AlternativeDAO alternativeDAO;
    private SPKModel model = new SPKModel();
    private List<Map<Object, Object>> rank = new ArrayList<>();
}

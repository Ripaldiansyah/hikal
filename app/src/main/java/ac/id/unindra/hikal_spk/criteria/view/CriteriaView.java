package ac.id.unindra.hikal_spk.criteria.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import com.formdev.flatlaf.FlatClientProperties;
import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.UI.Table.TableCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.category.view.CategoryAddView;
import ac.id.unindra.hikal_spk.criteria.controller.CriteriaController;
import ac.id.unindra.hikal_spk.report.controller.ReportController;
import ac.id.unindra.hikal_spk.utils.model.CategoryModel;
import ac.id.unindra.hikal_spk.utils.model.CriteriaModel;
import ac.id.unindra.hikal_spk.utils.model.TableModel.CriteriaTableModel;
import net.miginfocom.swing.MigLayout;

public class CriteriaView extends JPanel {

        public CriteriaView() {
                initComponent();
        }

        private void initComponent() {
                initLayout();
                setHeader();
                setContent();
                setFooter();
                initStyle();
                disableButton();
                initAdd();

        }

        private void initLayout() {
                setLayout(new MigLayout("fill,wrap, insets 0 5 0 0", "[fill]", "[fill]"));
                mainPanel = new JPanel(new MigLayout("fill,wrap, insets 10 10 0 10", "[fill,left]", "[top]"));
                headerPanel = new JPanel(new MigLayout("fill,wrap, insets 5", "[fill,left]", "[top]"));
                contentPanel = new JPanel(new MigLayout("fill,wrap, insets 0", "[fill, center]", "[fill,top]"));
                footerPanel = new JPanel(new MigLayout("insets 5, wrap", "[left]", "[top]"));
        }

        private void initStyle() {
                mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20;"
                                + "background:lighten(@background,10%)");
                headerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:lighten(@background,10%)");
                footerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:lighten(@background,10%)");
                lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold +10");
        }

        private void initAdd() {
                mainPanel.add(headerPanel);
                mainPanel.add(contentPanel);
                mainPanel.add(footerPanel, "push");
                add(mainPanel);

        }

        private void setHeader() {
                lbTitle = new JLabel("Kriteria");
                txtSearch = new TextFieldCustom(
                                "Cari nama Kriteria",
                                null,
                                true);
                txtSearch.addKeyListener(new KeyListener() {

                        @Override
                        public void keyTyped(KeyEvent e) {
                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                        }

                        @Override
                        public void keyReleased(KeyEvent e) {
                                criteriaTableModel.setData(controller.searchData(txtSearch.getText()));
                        }

                });
                IconCustom iconPrint = new IconCustom("svg/print.svg", 1f, null);
                btnPrint = new ButtonCustom(
                                "Cetak",
                                iconPrint.getIcon(),
                                "#a0a0a0",
                                (e) -> {
                                        ReportController reportController = new ReportController();
                                        reportController.ReportCriteria();
                                });
                IconCustom iconAdd = new IconCustom("svg/add.svg", 1f, null);
                btnAdd = new ButtonCustom(
                                "Tambah Kriteria",
                                iconAdd.getIcon(),
                                "#e7000a",
                                (e) -> {
                                        CriteriaAddView.isUpdate = false;
                                        changeContent(new CriteriaAddView("Tambah Kriteria"));
                                });
                headerPanel.add(lbTitle, "split 3");
                headerPanel.add(btnPrint, "w 100!, h 30!");
                headerPanel.add(btnAdd, "w 140!, h 30!, gapx 10");
                headerPanel.add(txtSearch, "h 30!, gapy 10");
        }

        private void setContent() {
                criteriaTableModel.setData(controller.getData());
                criteriaTable = new TableCustom(criteriaTableModel, null);
                criteriaTable.getTable().addMouseListener(new MouseInputAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                indexRow = criteriaTable.getSelectedRow();
                                enableButton();
                        }
                });
                contentPanel.add(criteriaTable);
        }

        private void setFooter() {
                IconCustom iconDelete = new IconCustom("svg/delete.svg", 1f, null);
                btnDelete = new ButtonCustom(
                                "Hapus Kriteria",
                                iconDelete.getIcon(),
                                "#e7000a",
                                (e) -> {
                                        controller.deleteData(criteriaTableModel, indexRow);
                                        disableButton();
                                });
                IconCustom iconChange = new IconCustom("svg/edit.svg", 1f, null);
                btnChange = new ButtonCustom(
                                "Ubah Kriteria",
                                iconChange.getIcon(),
                                "#a0a0a0",
                                (e) -> {
                                        updateCriteria();
                                });

                footerPanel.add(btnDelete, "w 140!, h 30!");
                footerPanel.add(btnChange, "w 140!, h 30!,gapy 5");

        }

        private void changeContent(JPanel panel) {
                removeAll();
                setLayout(new MigLayout("fill,wrap, insets 0", "[fill]", "[fill]"));
                add(panel);
                refreshUI();
        }

        private void refreshUI() {
                repaint();
                revalidate();
        }

        private void disableButton() {
                btnDelete.setEnabled(false);
                btnChange.setEnabled(false);
        }

        private void enableButton() {
                btnDelete.setEnabled(true);
                btnChange.setEnabled(true);
        }

        private void updateCriteria() {
                CriteriaModel model = new CriteriaModel();
                model.setCriteraID(criteriaTableModel.getSelectedIndex(indexRow).getCriteraID());
                model.setCriteriaName(criteriaTableModel.getSelectedIndex(indexRow).getCriteriaName());
                model.setCriteriaWeight(criteriaTableModel.getSelectedIndex(indexRow).getCriteriaWeight());
                model.setCriteriaType(criteriaTableModel.getSelectedIndex(indexRow).getCriteriaType());
                CriteriaAddView.isUpdate = true;
                CriteriaAddView.oldCriteriaName = criteriaTableModel.getSelectedIndex(indexRow).getCriteriaName();
                CriteriaAddView editCriteria = new CriteriaAddView("Ubah Kriteria");
                changeContent(editCriteria);
                editCriteria.setTextCriteriaEdit(model);
        }

        private int indexRow;
        private JPanel mainPanel;
        private JPanel headerPanel;
        private JPanel contentPanel;
        private JPanel footerPanel;
        private JLabel lbTitle;
        private TextFieldCustom txtSearch;
        private ButtonCustom btnAdd;
        private ButtonCustom btnPrint;
        private ButtonCustom btnDelete;
        private ButtonCustom btnChange;
        private CriteriaTableModel criteriaTableModel = new CriteriaTableModel();
        private TableCustom criteriaTable;
        private CriteriaController controller = new CriteriaController();

}

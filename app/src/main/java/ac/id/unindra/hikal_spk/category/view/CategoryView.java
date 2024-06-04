package ac.id.unindra.hikal_spk.category.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.event.MouseInputAdapter;
import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.UI.Table.TableCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.category.controller.CategoryController;
import ac.id.unindra.hikal_spk.register.view.RegisterView;
import ac.id.unindra.hikal_spk.utils.model.TableModel.category.CategoryTableModel;
import ac.id.unindra.hikal_spk.utils.model.category.CategoryModel;
import ac.id.unindra.hikal_spk.utils.model.user.UserModel;
import net.miginfocom.swing.MigLayout;

public class CategoryView extends JPanel {

        public CategoryView() {
                initComponent();
        }

        private void initComponent() {
                initLayout();
                setHeader();
                setContent();
                setFooter();
                initStyle();
                initAdd();
                disableButton();

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
                lbTitle = new JLabel("Kategori");
                txtSearch = new TextFieldCustom(
                                "Cari Kategori",
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
                                categoryTableModel.setData(controller.searchData(txtSearch.getText()));
                        }

                });
                IconCustom iconPrint = new IconCustom("svg/print.svg", 1f, null);
                btnPrint = new ButtonCustom(
                                "Cetak",
                                iconPrint.getIcon(),
                                "#a0a0a0",
                                (e) -> {

                                });
                IconCustom iconAdd = new IconCustom("svg/add.svg", 1f, null);
                btnAdd = new ButtonCustom(
                                "Tambah Kategori",
                                iconAdd.getIcon(),
                                "#e7000a",
                                (e) -> {
                                        changeContent(new CategoryAddView("Tambah Kategori"));
                                });
                headerPanel.add(lbTitle, "split 3");
                headerPanel.add(btnPrint, "w 100!, h 30!");
                headerPanel.add(btnAdd, "w 140!, h 30!, gapx 10");
                headerPanel.add(txtSearch, "h 30!, gapy 10");
        }

        private void setContent() {
                categoryTableModel.setData(controller.getData());
                categoryTable = new TableCustom(categoryTableModel, null);
                categoryTable.getTable().addMouseListener(new MouseInputAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                indexRow = categoryTable.getSelectedRow();
                                enableButton();
                        }
                });
                contentPanel.add(categoryTable);
        }

        private void setFooter() {
                IconCustom iconDelete = new IconCustom("svg/delete.svg", 1f, null);
                btnDelete = new ButtonCustom(
                                "Hapus Kategori",
                                iconDelete.getIcon(),
                                "#e7000a",
                                (e) -> {
                                        int index = categoryTable.getSelectedRow();
                                        if (index != -1) {
                                                controller.deleteData(categoryTableModel, indexRow);
                                                disableButton();
                                        }
                                });
                IconCustom iconChange = new IconCustom("svg/edit.svg", 1f, null);
                btnChange = new ButtonCustom(
                                "Ubah Kategori",
                                iconChange.getIcon(),
                                "#a0a0a0",
                                (e) -> {
                                        updateCategory();
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

        private void updateCategory() {
                CategoryModel model = new CategoryModel();
                model.setCategoryID(categoryTableModel.getSelectedIndex(indexRow).getCategoryID());
                model.setCategoryName(categoryTableModel.getSelectedIndex(indexRow).getCategoryName());
                CategoryAddView.isUpdate = true;
                CategoryAddView.oldCategoryName = categoryTableModel.getSelectedIndex(indexRow).getCategoryName();
                CategoryAddView editCategory = new CategoryAddView("Ubah Kategori");
                changeContent(editCategory);
                editCategory.setTextCategoryEdit(model);
        }

        private void disableButton() {
                btnDelete.setEnabled(false);
                btnChange.setEnabled(false);
        }

        private void enableButton() {
                btnDelete.setEnabled(true);
                btnChange.setEnabled(true);
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
        private CategoryTableModel categoryTableModel = new CategoryTableModel();
        private TableCustom categoryTable;
        private CategoryController controller = new CategoryController();

}

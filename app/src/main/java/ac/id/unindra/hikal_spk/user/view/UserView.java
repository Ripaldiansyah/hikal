package ac.id.unindra.hikal_spk.user.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.UI.Table.TableCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.register.view.RegisterView;
import ac.id.unindra.hikal_spk.report.controller.ReportController;
import ac.id.unindra.hikal_spk.user.controller.UserController;
import ac.id.unindra.hikal_spk.utils.model.UserModel;
import ac.id.unindra.hikal_spk.utils.model.TableModel.UserTableModel;
import net.miginfocom.swing.MigLayout;

public class UserView extends JPanel {

        public UserView() {
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
                contentPanel = new JPanel(
                                new MigLayout("fill,wrap, insets 0", "[fill, center]", "[fill,top]"));
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
                lbTitle = new JLabel("Pengguna");
                txtSearch = new TextFieldCustom(
                                "Cari Pengguna",
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
                                userTableModel.setData(controller.searchData(txtSearch.getText()));
                        }

                });

                IconCustom iconPrint = new IconCustom("svg/print.svg", 1f, null);
                btnPrint = new ButtonCustom(
                                "Cetak",
                                iconPrint.getIcon(),
                                "#a0a0a0",
                                (e) -> {
                                        ReportController reportController = new ReportController();
                                        reportController.ReportUser();
                                });
                IconCustom iconAdd = new IconCustom("svg/add.svg", 1f, null);
                btnAdd = new ButtonCustom(
                                "Tambah Pengguna",
                                iconAdd.getIcon(),
                                "#e7000a",
                                (e) -> {
                                        changeContent(new RegisterView("Register"));
                                });
                headerPanel.add(lbTitle, "split 3");
                headerPanel.add(btnPrint, "w 100!, h 30!");
                headerPanel.add(btnAdd, "w 140!, h 30!, gapx 10");
                headerPanel.add(txtSearch, "h 30!, gapy 10");
        }

        private void setContent() {
                userTableModel.setData(controller.getData());
                userTable = new TableCustom(userTableModel, null);
                userTable.getTable().addMouseListener(new MouseInputAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                indexRow = userTable.getSelectedRow();
                                enableButton();

                        }
                });
                contentPanel.add(userTable);
        }

        private void setFooter() {
                IconCustom iconDelete = new IconCustom("svg/delete.svg", 1f, null);
                btnDelete = new ButtonCustom(
                                "Hapus Pengguna",
                                iconDelete.getIcon(),
                                "#e7000a",
                                (e) -> {
                                        int index = userTable.getSelectedRow();
                                        if (index != -1) {
                                                controller.deleteData(userTableModel, indexRow);
                                                disableButton();
                                        }
                                });
                IconCustom iconChange = new IconCustom("svg/edit.svg", 1f, null);
                btnChange = new ButtonCustom(
                                "Ubah Pengguna",
                                iconChange.getIcon(),
                                "#a0a0a0",
                                (e) -> {
                                        updateUser();
                                });

                footerPanel.add(btnDelete, "w 140!, h 30!");
                footerPanel.add(btnChange, "w 140!, h 30!,gapy 5");

        }

        private void changeContent(JPanel panel) {
                removeAll();
                RegisterView.isFromLogin = false;
                setLayout(new MigLayout("fill,wrap, insets 0", "[fill]", "[fill]"));
                add(panel);
                refreshUI();
        }

        private void refreshUI() {
                repaint();
                revalidate();
        }

        private void updateUser() {
                UserModel model = new UserModel();
                model.setUserID(userTableModel.getSelectedIndex(indexRow).getUserID());
                model.setUsername(userTableModel.getSelectedIndex(indexRow).getUsername());
                model.setFullname(userTableModel.getSelectedIndex(indexRow).getFullname());
                model.setGender(userTableModel.getSelectedIndex(indexRow).getGender());
                model.setRole(userTableModel.getSelectedIndex(indexRow).getRole());
                RegisterView.isFromLogin = false;
                RegisterView.isUpdateUser = true;
                RegisterView.isFromSetting = false;
                RegisterView.oldUsername = userTableModel.getSelectedIndex(indexRow).getUsername();
                RegisterView editUser = new RegisterView("Ubah");
                changeContent(editUser);
                editUser.setTextUserEdit(model);
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
        private UserTableModel userTableModel = new UserTableModel();
        private TableCustom userTable;
        private UserController controller = new UserController();

}

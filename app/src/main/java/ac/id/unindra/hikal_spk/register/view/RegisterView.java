package ac.id.unindra.hikal_spk.register.view;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.hikal_spk.Main;
import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.UI.PasswordField.PasswordFieldCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.login.view.LoginView;
import ac.id.unindra.hikal_spk.register.controller.RegisterController;
import ac.id.unindra.hikal_spk.user.view.UserView;
import ac.id.unindra.hikal_spk.utils.model.user.UserModel;

public class RegisterView extends JPanel {

        public RegisterView(String title) {
                this.title = title;
                initComponent();
                addComponent();

        }

        private void initComponent() {
                setLayout(new MigLayout("fill,wrap,insets 0 10 40 40", "[left]", "[top]"));
                panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "fill,250:280"));
                lbTitle = new JLabel(title);
                txtFullname = new TextFieldCustom(
                                "Masukan nama Lengkap",
                                null,
                                true);
                txtUsername = new TextFieldCustom(
                                "Masukan nama pengguna",
                                null,
                                true);
                txtPassword = new PasswordFieldCustom(
                                "Masukan Kata andi Anda",
                                null,
                                true);
                txtPasswordConfirm = new PasswordFieldCustom(
                                "Konfirmasi Kata andi Anda",
                                null,
                                true);
                btnSubmit = new ButtonCustom(
                                "Submit",
                                null,
                                "#e7000a",
                                (e) -> {
                                        submit();
                                });
                cbRole = new JComboBox<>(role);
                IconCustom iconBack = new IconCustom("svg/back.svg", 1f, "#000000");
                btnBack = new ButtonCustom(
                                null,
                                iconBack.getIcon(),
                                "#f2f2f2",
                                (e) -> {
                                        if (isFromLogin) {
                                                Main.content.loginRegisterPanel(new LoginView());
                                        } else {
                                                backToUserView();
                                        }

                                });

                panel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20;"
                                + "background:lighten(@background,3%)");
                lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold +10");
                cbRole.putClientProperty(FlatClientProperties.STYLE, ""
                                + "focusColor:#e7000a;"
                                + "focusedBorderColor:#e7000a");
                cbRole.setRenderer(new DefaultListCellRenderer() {
                        @Override
                        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                        boolean isSelected, boolean cellHasFocus) {
                                Component component = super.getListCellRendererComponent(list, value, index, isSelected,
                                                cellHasFocus);
                                if (isSelected) {
                                        component.setBackground(Color.decode("#e7000a"));
                                        component.setForeground(Color.WHITE);
                                } else {
                                        component.setBackground(Color.WHITE);
                                        component.setForeground(Color.BLACK);
                                }
                                return component;
                        }
                });

                txtComponent = new TextFieldCustom[2];
                txtPasswordComponent = new PasswordFieldCustom[2];

        }

        private void backToUserView() {
                setLayout(new MigLayout("fill,wrap, insets 0 5 0 0", "[fill]",
                                "[fill]"));
                changeContent(new UserView());
        }

        private void addComponent() {
                add(btnBack);
                panel.add(lbTitle, "w 90!, center");
                panel.add(new JLabel("Nama Lengkap"), "gapy 8");
                panel.add(txtFullname.getTextField());
                panel.add(new JLabel("Jenis Kelamin"), "gapy 8");
                panel.add(gender());
                panel.add(new JLabel("Nama Pengguna"), "gapy 8");
                panel.add(txtUsername.getTextField());
                panel.add(new JLabel("Kata Sandi"), "gapy 8");
                panel.add(txtPassword.getPasswordField());
                panel.add(new JLabel("Kata Sandi Konfirmasi"), "gapy 8");
                panel.add(txtPasswordConfirm.getPasswordField());
                if (!isFromLogin) {
                        panel.add(new JLabel("Pilih Role"), "gapy 8");
                        panel.add(cbRole, "h 40!");
                }
                panel.add(btnSubmit, "gapy 8");
                add(panel, "center, gapy 30");
                txtComponent[0] = txtFullname;
                txtComponent[1] = txtUsername;
                txtPasswordComponent[0] = txtPassword;
                txtPasswordComponent[1] = txtPasswordConfirm;
        }

        private void changeContent(JPanel panel) {
                removeAll();
                add(panel);
                refreshUI();
        }

        private void refreshUI() {
                repaint();
                revalidate();
        }

        private JPanel gender() {
                JPanel panel = new JPanel(new MigLayout("insets 0"));
                panel.putClientProperty(FlatClientProperties.STYLE, "" +
                                "background:null");
                genderMale = new JRadioButton("Laki-Laki");
                genderFemale = new JRadioButton("Perempuan");
                groupGender = new ButtonGroup();
                groupGender.add(genderMale);
                groupGender.add(genderFemale);
                genderMale.setSelected(true);
                panel.add(genderMale);
                panel.add(genderFemale);
                return panel;
        }

        private void submit() {
                controller.getInput(model, txtFullname, txtUsername, genderMale, txtPassword, cbRole);
                if (controller.fieldNotEmpty(txtComponent)) {
                        if (controller.passwordValidation(txtPasswordComponent)) {

                                if (isUpdateUser) {
                                        handleUserUpdate();
                                } else {
                                        handleUserCreation();
                                }
                        }
                }
        }

        private void handleUserUpdate() {
                if (model.getUsername().equals(oldUsername)) {
                        updateUser();
                } else {
                        if (controller.usernameIsAvailable(model)) {
                                updateUser();
                        } else {
                                controller.notificationIsnAvail();
                        }
                }

                backToUserView();
        }

        private void handleUserCreation() {
                if (controller.usernameIsAvailable(model)) {
                        controller.createUser(model);
                        controller.notificationCreate();
                        clearField();
                } else {
                        controller.notificationIsnAvail();
                }
        }

        private void updateUser() {
                controller.updateUser(model);
                controller.notificationUpdate();
                clearField();
        }

        private void clearField() {
                txtFullname.setText(null);
                txtUsername.setText(null);
                txtPassword.setText(null);
                txtPasswordConfirm.setText(null);
                genderMale.isSelected();
                cbRole.setSelectedIndex(0);
        }

        public void setTextUserEdit(UserModel model) {
                txtFullname.setText(model.getFullname());
                txtUsername.setText(model.getUsername());
                if (model.getGender().equalsIgnoreCase("Perempuan")) {
                        genderFemale.setSelected(true);
                } else {
                        genderMale.setSelected(true);
                }
                cbRole.setSelectedItem(model.getRole());

                this.model = model;
        }

        public static String oldUsername;
        private JPanel panel;
        private JLabel lbTitle;
        private TextFieldCustom txtFullname;
        private TextFieldCustom txtUsername;
        private PasswordFieldCustom txtPassword;
        private PasswordFieldCustom txtPasswordConfirm;
        private ButtonCustom btnSubmit;
        private ButtonCustom btnBack;
        private JComboBox<String> cbRole;
        private String[] role = { "Pengguna", "Admin", };
        public static boolean isFromLogin;
        public static boolean isUpdateUser;
        private String title;
        private JRadioButton genderMale;
        private JRadioButton genderFemale;
        private ButtonGroup groupGender;
        private UserModel model = new UserModel();
        TextFieldCustom[] txtComponent;
        PasswordFieldCustom[] txtPasswordComponent;

        private RegisterController controller = new RegisterController();
}

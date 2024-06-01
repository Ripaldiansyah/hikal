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
import ac.id.unindra.hikal_spk.user.view.UserView;

public class RegisterView extends JPanel {

        public RegisterView() {
                initComponent();
                addComponent();
        }

        private void initComponent() {
                setLayout(new MigLayout("fill,wrap,insets 0 10 40 40", "[left]", "[top]"));
                panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "fill,250:280"));
                lbTitle = new JLabel("Register");
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
                btnRegister = new ButtonCustom(
                                "Daftar",
                                null,
                                "#e7000a",
                                (e) -> {

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
                                                setLayout(new MigLayout("fill,wrap, insets 0 5 0 0", "[fill]",
                                                                "[fill]"));
                                                changeContent(new UserView());
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
        }

        private void addComponent() {
                add(btnBack);
                panel.add(lbTitle, "w 90!, center");
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
                panel.add(btnRegister, "gapy 8");
                add(panel, "center, gapy 30");
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

        JPanel panel;
        JLabel lbTitle;
        TextFieldCustom txtUsername;
        PasswordFieldCustom txtPassword;
        PasswordFieldCustom txtPasswordConfirm;
        ButtonCustom btnRegister;
        ButtonCustom btnBack;
        JComboBox<String> cbRole;
        String[] role = { "Admin", "Pengguna" };
        public static boolean isFromLogin;
}

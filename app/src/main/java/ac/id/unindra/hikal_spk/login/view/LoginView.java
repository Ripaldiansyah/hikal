package ac.id.unindra.hikal_spk.login.view;

import ac.id.unindra.hikal_spk.Main;
import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.PasswordField.PasswordFieldCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.login.controller.LoginController;
import ac.id.unindra.hikal_spk.menu.view.MenuView;
import ac.id.unindra.hikal_spk.register.view.RegisterView;
import ac.id.unindra.hikal_spk.utils.MD5;
import ac.id.unindra.hikal_spk.utils.model.user.UserModel;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class LoginView extends JPanel {

        public LoginView() {
                initComponent();
                addComponent();
        }

        private void initComponent() {
                setLayout(new MigLayout("fill,insets 40", "[right]", "[center]"));
                panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "fill,250:280"));
                lbTitle = new JLabel("Login");
                txtUsername = new TextFieldCustom(
                                "Masukan nama pengguna Anda",
                                null,
                                true);
                txtPassword = new PasswordFieldCustom(
                                "Masukan Kata andi Anda",
                                null,
                                true);

                btnLogin = new ButtonCustom(
                                "Masuk",
                                null,
                                "#e7000a",
                                (e) -> {
                                        if (isRegistered()) {
                                                Main.content.changeContentPanel(new MenuView());
                                        }
                                });
                btnRegister = new ButtonCustom(
                                "Daftar",
                                null,
                                "#a8a8a8",
                                (e) -> {
                                        RegisterView.isFromLogin = true;
                                        Main.content.loginRegisterPanel(new RegisterView("Register"));
                                });

                panel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20;"
                                + "background:lighten(@background,3%)");

                lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold +10");

        }

        private void addComponent() {
                panel.add(lbTitle, "w 60!, center");
                panel.add(new JLabel("Nama Pengguna"), "gapy 8");
                panel.add(txtUsername.getTextField());
                panel.add(new JLabel("Kata Sandi"), "gapy 8");
                panel.add(txtPassword.getPasswordField());
                panel.add(btnLogin, "gapy 15");
                panel.add(new JLabel("Atau"), "w 28!, center");
                panel.add(btnRegister, "gapy 5");
                add(mitsubishiLogo());
                add(panel);
        }

        private JPanel mitsubishiLogo() {
                JPanel panelIcon = new JPanel(new MigLayout());
                Icon imgIcon = new ImageIcon(this.getClass().getResource("/Image/mitsubishi_logo.gif"));
                JLabel mitsubishiLogo = new JLabel(imgIcon);
                panelIcon.add(mitsubishiLogo);
                return panelIcon;
        }

        boolean validation() {
                if (txtUsername.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nama pengguna tidak boleh Kosong",
                                        "Nama pengguna tidak valid",
                                        JOptionPane.WARNING_MESSAGE);
                        return false;
                } else if (txtPassword.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Kata sandi tidak boleh Kosong", "Kata sandi tidak valid",
                                        JOptionPane.WARNING_MESSAGE);
                        return false;
                }
                return true;
        }

        void getInput() {
                String username = txtUsername.getText().toLowerCase();
                String password = MD5.getMD5(txtPassword.getText().toLowerCase());
                model.setUsername(username);
                model.setPassword(password);
        }

        boolean isRegistered() {
                getInput();
                if (validation()) {
                        if (!controller.isRegistered(model)) {
                                JOptionPane.showMessageDialog(null, "Akun tidak sesuai atau tidak terdaftar",
                                                "Gagal Login",
                                                JOptionPane.ERROR_MESSAGE);
                                return false;
                        }
                } else {
                        return false;
                }
                return true;
        }

        UserModel model = new UserModel();
        LoginController controller = new LoginController();
        JPanel panel;
        TextFieldCustom txtUsername;
        PasswordFieldCustom txtPassword;
        ButtonCustom btnLogin;
        ButtonCustom btnRegister;
        JLabel lbTitle;

}

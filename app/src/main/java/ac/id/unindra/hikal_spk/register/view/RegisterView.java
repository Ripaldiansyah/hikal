package ac.id.unindra.hikal_spk.register.view;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.hikal_spk.Main;
import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.UI.PasswordField.PasswordFieldCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.login.view.LoginView;

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

                IconCustom iconBack = new IconCustom("svg/back.svg", 1f, "#000000");
                btnBack = new ButtonCustom(
                                null,
                                iconBack.getIcon(),
                                "#f2f2f2",
                                (e) -> {
                                        Main.content.loginRegisterPanel(new LoginView());
                                });

                panel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20;"
                                + "background:lighten(@background,3%)");
                lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold +10");
                txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                                + "showRevealButton:true");
                txtPasswordConfirm.putClientProperty(FlatClientProperties.STYLE, ""
                                + "showRevealButton:true");
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
                panel.add(btnRegister, "gapy 8");
                add(panel, "center, gapy 30");
        }

        JPanel panel;
        JLabel lbTitle;
        TextFieldCustom txtUsername;
        PasswordFieldCustom txtPassword;
        PasswordFieldCustom txtPasswordConfirm;
        ButtonCustom btnRegister;
        ButtonCustom btnBack;
}

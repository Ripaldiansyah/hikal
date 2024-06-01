package ac.id.unindra.hikal_spk.login.view;

import ac.id.unindra.hikal_spk.Main;
import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.PasswordField.PasswordFieldCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.menu.view.MenuView;
import ac.id.unindra.hikal_spk.register.view.RegisterView;

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
                                        Main.content.changeContentPanel(new MenuView());
                                });
                btnRegister = new ButtonCustom(
                                "Daftar",
                                null,
                                "#a8a8a8",
                                (e) -> {
                                        RegisterView.isFromLogin = true;
                                        Main.content.loginRegisterPanel(new RegisterView());
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

        JPanel panel;
        TextFieldCustom txtUsername;
        PasswordFieldCustom txtPassword;
        ButtonCustom btnLogin;
        ButtonCustom btnRegister;
        JLabel lbTitle;

}

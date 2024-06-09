package ac.id.unindra.hikal_spk;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Locale;

import javax.swing.*;

import java.awt.Font;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.util.UIScale;

import ac.id.unindra.hikal_spk.login.view.LoginView;
import ac.id.unindra.hikal_spk.menu.view.MenuView;
import ac.id.unindra.hikal_spk.user.view.UserView;

public class Main extends JFrame {
    public static Main content;

    public Main() {
        initUI();
    }

    private void configureFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(UIScale.scale(new Dimension(1366, 768)));
        // setSize(UIScale.scale(new Dimension(700, 600)));
        setLocationRelativeTo(null);
    }

    private void initUI() {
        configureFrame();
        setContentPane(new LoginView());
    }

    private void refreshUI() {
        revalidate();
        repaint();
    }

    public void loginRegisterPanel(JPanel newPanel) {
        setContentPane(newPanel);
        configureFrame();
        refreshUI();
    }

    public void changeContentPanel(JPanel newPanel) {
        setSize(UIScale.scale(new Dimension(1366, 768)));
        setLocationRelativeTo(null);
        setContentPane(newPanel);
        refreshUI();
    }

    private static void configureApplication() {
        FlatRobotoFont.install();
        Locale.setDefault(new Locale("id"));
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatIntelliJLaf.setup();

    }

    public static void main(String[] args) {
        configureApplication();
        java.awt.EventQueue.invokeLater(() -> {
            content = new Main();
            content.setVisible(true);
        });
    }
}

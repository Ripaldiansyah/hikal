package ac.id.unindra.hikal_spk.menu.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.dashboard.view.DashboardView;
import ac.id.unindra.hikal_spk.menu.controller.MenuController;
import ac.id.unindra.hikal_spk.utils.UserToken;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;

public class MenuView extends JPanel {

    public MenuView() {
        initComponent();
        isMenuVisible = true;
        isVisible = true;
        initMenu();
        mainPanel.add(menuPanel, "dock west");
    }

    private void initComponent() {
        initLayout();
        initStyle();
        IconCustom iconMenu = new IconCustom("svg/menu.svg", 1f, null);
        btnMenu = new ButtonCustom(
                null,
                iconMenu.getIcon(),
                "#d80009",
                (e) -> {
                    isVisible = !isVisible;
                    isMenuVisible = true;
                    if (isVisible) {
                        initMenu();
                        mainPanel.add(menuPanel, "dock west");

                    }
                    refreshUI();
                });

        // add first Content
        // test();
        changeContent(new DashboardView());
        changeMenuDisplay();
        mainPanel.add(contentPanel, "grow, push");
        add(mainPanel, "grow");
    }

    // void test() {
    // test1.add("alternatif");
    // test1.add("alternatif");
    // test1.add("alternatif");

    // test2.add("kriteria");
    // test2.add("kriteria");

    // }

    // List<String> test1 = new ArrayList<>();
    // List<String> test2 = new ArrayList<>();

    public static void changeContent(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);
        refreshUI();
    }

    private void changeMenuDisplay() {
        menuPanel.add(btnMenu, "north, gapy 30");
        initMenu();
        mainPanel.add(menuPanel, "dock west");
    }

    private void initLayout() {
        setLayout(new MigLayout("insets 0, fill"));
        mainPanel = new JPanel(new MigLayout("insets 0, fill", "[left][push]", "[fill]"));
        menuPanel = new JPanel(new MigLayout("wrap, insets 20 0 20 0", "fill, 30:150", "fill"));
        contentPanel = new JPanel(new MigLayout("wrap, fillx, insets 0", "fill", "fill, grow"));
    }

    private void initMenu() {
        if (isMenuVisible) {
            menuPanel.removeAll();
            menuPanel.add(logo());
        }

        String[] title = {
                "Dashboard",
                "Pengguna",
                "Kategori",
                "Alternatif",
                "Kriteria",
                "SPK",
                "Laporan",
                "Pengaturan",
                "Logout" };

        String[] path = {
                "svg/dashboard.svg",
                "svg/user.svg",
                "svg/category.svg",
                "svg/alternative.svg",
                "svg/criteria.svg",
                "svg/spk.svg",
                "svg/report.svg",
                "svg/setting.svg",
                "svg/logout.svg" };

        ActionListener[] actions = {
                e -> changeContent(controller.dashboard()),
                e -> changeContent(controller.user()),
                e -> changeContent(controller.category()),
                e -> changeContent(controller.alternative()),
                e -> changeContent(controller.criteria()),
                e -> changeContent(controller.spk()),
                e -> changeContent(controller.report()),
                e -> changeContent(controller.setting()),
                e -> controller.logout()
        };

        Icon[] icons = new Icon[path.length];
        IconCustom[] iconMenuArray = new IconCustom[path.length];
        ButtonCustom[] btnMenuArray = new ButtonCustom[title.length];

        for (int i = 0; i < path.length; i++) {
            iconMenuArray[i] = new IconCustom(path[i], 1f, null);
            icons[i] = iconMenuArray[i].getIcon();
        }

        for (int i = 0; i < title.length; i++) {
            if (UserToken.role.equalsIgnoreCase("admin")) {
                btnMenuArray[i] = new ButtonCustom(title[i], icons[i], "#d80009", actions[i]);
                if (isMenuVisible) {
                    btnMenuArray[i].setHorizontalAlignment(SwingConstants.LEFT);
                    btnMenuArray[i].setBorder(new EmptyBorder(0, 13, 0, 0));
                }

                if (i == 0) {
                    menuPanel.add(btnMenuArray[i].getButton(), "gapy 56, h 50!");
                } else {
                    menuPanel.add(btnMenuArray[i].getButton(), "gapy 0, h 50!");
                }
            } else {
                boolean isUser = i == 1 || i == 2 || i == 3 || i == 4 || i == 6;
                if (!isUser) {
                    btnMenuArray[i] = new ButtonCustom(isMenuVisible ? title[i] : null, icons[i], "#d80009",
                            actions[i]);
                    if (isMenuVisible) {
                        btnMenuArray[i].setHorizontalAlignment(SwingConstants.LEFT);
                        btnMenuArray[i].setBorder(new EmptyBorder(0, 13, 0, 0));
                    }

                    if (i == 0) {
                        menuPanel.add(btnMenuArray[i].getButton(),
                                isMenuVisible ? "gapy 56, h 50!" : "gapy 100, h 30!");
                    } else {
                        menuPanel.add(btnMenuArray[i].getButton(), isMenuVisible ? "gapy 0, h 50!" : "gapy 20, h 30!");
                    }
                }
            }

        }

    }

    private void initStyle() {
        menuPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "background:#d80009");
    }

    private JLabel logo() {
        Icon imgIcon = new ImageIcon(this.getClass().getResource("/Image/mitsubishi.png"));
        JLabel mitsubishiLogo = new JLabel(imgIcon);
        return mitsubishiLogo;
    }

    private static void refreshUI() {
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    static JPanel mainPanel;
    JPanel menuPanel;
    static JPanel contentPanel;
    ButtonCustom btnMenu;
    boolean isVisible = false;
    boolean isMenuVisible = false;
    MenuController controller = new MenuController();

}

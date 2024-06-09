package ac.id.unindra.hikal_spk.dashboard.view;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Font;

import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.dashboard.controller.DashboardController;
import ac.id.unindra.hikal_spk.utils.UserToken;
import net.miginfocom.swing.MigLayout;

public class DashboardView extends JPanel {

        public DashboardView() {
                initComponent();
        }

        private void initComponent() {
                initLayout();
                setHeader();
                setRightPanel();
                initAdd();
                initStyle();

        }

        private void initLayout() {
                setLayout(new MigLayout("fill,wrap, insets 0 5 0 0", "[fill]", "[fill]"));
                mainPanel = new JPanel(new MigLayout("fill, insets 10 10 0 10", "[fill,left]", "[fill,top]"));
                headerPanel = new JPanel(new MigLayout("fill,wrap, insets 5", "[fill,left]", "[top]"));
                contentPanel = new JPanel(new MigLayout("fill,wrap, insets 5", "[fill, center]", "[fill,top]"));
                footerPanel = new JPanel(new MigLayout("insets 5, wrap", "[left]", "[top]"));
                leftPanel = new JPanel(new MigLayout("fill,wrap, insets 5", "[fill,left]", "[top]"));
                rightPanel = new JPanel(new MigLayout("fill,wrap, insets 10", "[fill,center, 200:220]", "[top]"));
        }

        private void initStyle() {
                mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20;"
                                + "background:#f1f1fb");
                headerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:#f1f1fb");
                contentPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:#f1f1fb");
                footerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:#f1f1fb");
                leftPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:#f1f1fb");
                rightPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20;"
                                + "background:#ececf6");
                lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold +10");
                lbName.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font: 14;"
                                + "Foreground : #949292");

        }

        private void initAdd() {
                leftPanel.add(headerPanel);
                leftPanel.add(contentPanel);
                leftPanel.add(footerPanel);
                mainPanel.add(leftPanel, "push");
                mainPanel.add(rightPanel);
                add(mainPanel);
        }

        private void setHeader() {
                lbTitle = new JLabel("Dashboard");
                lbName = new JLabel("Hai, " + UserToken.fullname);
                headerPanel.add(lbTitle);
                headerPanel.add(lbName);
                headerPanel.add(headerCard(), "gapy 20");
        }

        private void setRightPanel() {
                JLabel lbtitleRight = new JLabel("Universitas Indraprasta");
                JLabel lbtitle2Right = new JLabel("PGRI Jakarta");
                JLabel lbName = new JLabel("Fiqri Hikal | 202043500016 | SA");
                JLabel lbAboutApp1 = new JLabel(
                                "Sistem Pendukung Keputusan ");
                JLabel lbAboutApp2 = new JLabel(
                                "Pembelian Mobil Mitsubishi");
                JLabel lbAboutApp3 = new JLabel(
                                "Dengan Metode SAW");

                lbtitleRight.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold +4");
                lbtitle2Right.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold +4");
                lbName.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold;"
                                + "Foreground : #949292");
                lbAboutApp1.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:14;"
                                + "Foreground : #949292");
                lbAboutApp2.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:14;"
                                + "Foreground : #949292");
                lbAboutApp3.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:14;"
                                + "Foreground : #949292");

                rightPanel.add(logo("/Image/unindra_logo.png"), "gapy 20");
                rightPanel.add(lbtitleRight, "w 190! ,center");
                rightPanel.add(lbtitle2Right, "w 101!, center,push");
                rightPanel.add(lbName, " gapy 20");
                rightPanel.add(lbAboutApp1, " gapy 5");
                rightPanel.add(lbAboutApp2, " gapy 5");
                rightPanel.add(lbAboutApp3, " gapy 5");

        }

        private JPanel card(String title, String description, String path) {
                JPanel panel = new JPanel(new MigLayout("fill, wrap, insets 20", "[center]", "[ center]"));
                JLabel lbCardTitle = new JLabel(title);
                JLabel lbCardDescription = new JLabel(description);
                IconCustom icon = new IconCustom(path, 2, "#d80009");

                panel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20;"
                                + "background:lighten(@background,3%)");
                lbCardTitle.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold +10");
                lbCardDescription.putClientProperty(FlatClientProperties.STYLE, ""
                                + "Foreground : #949292");

                panel.add(lbCardTitle);
                panel.add(new JLabel(icon.getIcon()));
                panel.add(lbCardDescription);

                return panel;
        }

        private JPanel headerCard() {
                JPanel panel = new JPanel(new MigLayout("fill, insets 0", "[fill,left]", "[center]"));
                panel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20;"
                                + "background:#f1f1fb");

                String[] title = {
                                controller.getCountUser(),
                                controller.getCountCategory(),
                                controller.getCountCriteria(),
                                controller.getCountAlternative(),
                                controller.getCountSpk()
                };
                String[] description = {
                                "Pengguna Terdaftar",
                                "Kategori Terdaftar",
                                "Kriteria Terdaftar",
                                "Alternatif Terdaftar",
                                "Keputusan Terhitung"
                };
                String[] path = {
                                "svg/user.svg",
                                "svg/category.svg",
                                "svg/criteria.svg",
                                "svg/alternative.svg",
                                "svg/spk.svg"
                };

                for (int i = 0; i < title.length; i++) {
                        if (i == 0) {
                                panel.add(card(title[i], description[i], path[i]));
                        } else {
                                panel.add(card(title[i], description[i], path[i]), "gapx 10");
                        }
                }

                return panel;

        }

        private JLabel logo(String path) {
                Icon imgIcon = new ImageIcon(this.getClass().getResource(path));
                JLabel logo = new JLabel(imgIcon);
                return logo;
        }

        JPanel mainPanel;
        JPanel rightPanel;
        JPanel leftPanel;
        JPanel headerPanel;
        JPanel contentPanel;
        JPanel footerPanel;
        JLabel lbTitle;
        JLabel lbName;
        DashboardController controller = new DashboardController();

}

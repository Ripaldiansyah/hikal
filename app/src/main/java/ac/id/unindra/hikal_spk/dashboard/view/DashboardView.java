package ac.id.unindra.hikal_spk.dashboard.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import net.miginfocom.swing.MigLayout;

public class DashboardView extends JPanel {

    public DashboardView() {
        initComponent();
    }

    private void initComponent() {
        initLayout();
        setHeader();

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
        rightPanel = new JPanel(new MigLayout("fill,wrap, insets 5", "[fill, center, 200:220]", "[fill,top]"));
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
        lbName = new JLabel("Hai, Ripal");
        headerPanel.add(lbTitle);
        headerPanel.add(lbName);
        headerPanel.add(headerCard(), "gapy 20");
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
        JPanel panel = new JPanel(new MigLayout("fill, insets 0", "[left]", "[center]"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:#f1f1fb");

        String[] title = {
                "400",
                "100",
                "200",
                "300",
                "170"
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
                panel.add(card(title[i], description[i], path[i]), "gapx 5");
            }
        }

        return panel;

    }

    JPanel mainPanel;
    JPanel rightPanel;
    JPanel leftPanel;
    JPanel headerPanel;
    JPanel contentPanel;
    JPanel footerPanel;
    JLabel lbTitle;
    JLabel lbName;

}

package ac.id.unindra.hikal_spk.alternative.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import net.miginfocom.swing.MigLayout;

public class AlternativeAddView extends JPanel {

    public AlternativeAddView() {
        initComponents();
    }

    private void initComponents() {
        initLayout();
        initAdd();
        setContent();
        initStyle();
    }

    private void initLayout() {
        setLayout(new MigLayout("fill,wrap, insets 0 5 0 0", "[fill]", "[fill]"));
        mainPanel = new JPanel(new MigLayout("fill,wrap, insets 10 10 0 10", "[left]", "[top]"));
        contentPanel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "fill,250:280"));
    }

    private void initStyle() {
        mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20");
        contentPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:lighten(@background,3%)");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +10");
        cbAlternative.putClientProperty(FlatClientProperties.STYLE, ""
                + "focusColor:#e7000a;"
                + "focusedBorderColor:#e7000a");
        cbAlternative.setRenderer(new DefaultListCellRenderer() {
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

    private void initAdd() {
        IconCustom iconBack = new IconCustom("svg/back.svg", 1f, "#000000");
        btnBack = new ButtonCustom(
                null,
                iconBack.getIcon(),
                "#f2f2f2",
                (e) -> {
                    changeContent(new AlternativeView());
                });
        mainPanel.add(btnBack);
        mainPanel.add(contentPanel, "center, gapy 30");
        add(mainPanel);
    }

    private void setContent() {
        lbTitle = new JLabel("Tambah Alternatif");
        JLabel lbCriteriaName = new JLabel("Nama Alternatif");
        JLabel lbCriteriaAlternative = new JLabel("Kategori Alternatif");
        txtCriteriaName = new TextFieldCustom(
                "Masukan Nama Alternatif",
                null,
                true);
        cbAlternative = new JComboBox<>(alternative);
        btnSave = new ButtonCustom(
                "Simpan",
                null,
                "#e7000a",
                (e) -> {

                });

        contentPanel.add(lbTitle, "w 190!, center");
        contentPanel.add(lbCriteriaName, "gapy 20");
        contentPanel.add(txtCriteriaName, "gapy 0, h 35!");
        contentPanel.add(lbCriteriaAlternative, "gapy 10");
        contentPanel.add(cbAlternative, "gapy 0, h 35!");
        contentPanel.add(btnSave, "gapy 10, h 35!");
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

    JPanel mainPanel;
    JPanel contentPanel;
    TextFieldCustom txtCriteriaName;
    ButtonCustom btnSave;
    ButtonCustom btnBack;
    JComboBox<String> cbAlternative;
    String[] alternative = { "Admin", "Pengguna" };
    JLabel lbTitle;
}

package ac.id.unindra.hikal_spk.spk.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.formdev.flatlaf.FlatClientProperties;
import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.spk.controller.SPKController;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;

public class SPKSelectView extends JPanel {

    public SPKSelectView() {
        initComponents();

    }

    private void initComponents() {
        initLayout();
        addPanel(selectingCategory);
        setSelectingCategory();
        initStyle();
    }

    private void initLayout() {
        setLayout(new MigLayout("fill,wrap, insets 0 5 0 0", "[fill]", "[fill]"));
        mainPanel = new JPanel(new MigLayout("fill,wrap, insets 10 10 0 10", "[left]", "[top]"));
        selectingCategory = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "fill,250:280"));
        selectingAlternative = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "fill,250:280"));
        selectingCriteria = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "fill,250:280"));

    }

    private void initStyle() {
        mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20");
        selectingCategory.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:lighten(@background,7%)");

        selectingAlternative.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:lighten(@background,7%)");

        selectingCriteria.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:lighten(@background,7%)");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +5");

    }

    private void setSelectingCategory() {
        String[] categoryArray = new String[controller.getCategory().size()];
        int i = 0;
        for (String category : controller.getCategory()) {
            categoryArray[i++] = category;
        }
        lbTitle = new JLabel("Pilih Kategori");
        cbCategory = new JComboBox<>(categoryArray);
        btnConfirm = new ButtonCustom(
                "Lanjut",
                null,
                "#d80009",
                (e) -> {
                    String categoryName = cbCategory.getSelectedItem().toString();
                    categoryId = controller.getCategoryID(categoryName);

                    setSelectingAlternative();
                    addPanel(selectingAlternative);
                    indexPanel++;
                });
        selectingCategory.add(lbTitle, "w 110!, center");
        selectingCategory.add(cbCategory, "h 35!, gapy 20");
        selectingCategory.add(btnConfirm, "gapy 5, h 30!");
    }

    private void setSelectingAlternative() {
        selectingAlternative.removeAll();
        cbAlternativeList.clear();
        JScrollPane scrollPane = new JScrollPane();
        JPanel checkBoxPanel = new JPanel(new MigLayout());
        List<String> alternativeList = controller.getAlternative(categoryId);

        if (alternativeList.isEmpty()) {
            selectingAlternative.add(noData());
        } else {
            checkBoxPanel.setLayout(new MigLayout("wrap"));
            checkBoxPanel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "background:lighten(@background,7%)");

            for (String alternative : alternativeList) {
                cbAlternative = new JCheckBox(alternative);
                checkBoxPanel.add(cbAlternative, "h 30!");
                cbAlternativeList.add(cbAlternative);
                if (!alternativeSelectedList.isEmpty()) {
                    if (alternativeSelectedList.contains(cbAlternative.getText())) {
                        cbAlternative.setSelected(true);
                    }
                }
            }
            lbTitle = new JLabel("Pilih Alternatif");
            initStyle();
            scrollPane.putClientProperty(FlatClientProperties.STYLE, ""
                    + "border:0,0,0,0");
            scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                    + "trackArc:999;"
                    + "width:10");
            scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            scrollPane.setViewportView(checkBoxPanel);
            scrollPane.getViewport().setPreferredSize(new Dimension(300, 120));
            btnConfirm = new ButtonCustom(
                    "Lanjut",
                    null,
                    "#d80009",
                    (e) -> {

                        if (validationInputAlternative()) {
                            setSelectingCriteria();
                            addPanel(selectingCriteria);
                            indexPanel++;
                        } else {
                            controller.notificationAlternativeNotSelected();
                        }

                    });
            selectingAlternative.add(lbTitle, "w 130!, center");
            selectingAlternative.add(checkBoxPanel);
            selectingAlternative.add(btnConfirm, "gapy 5, h 30!");
        }
        recentPanel = selectingCategory;
    }

    private void setSelectingCriteria() {
        selectingCriteria.removeAll();
        cbCriteriaList.clear();
        JScrollPane scrollPane = new JScrollPane();
        JPanel checkBoxPanel = new JPanel(new MigLayout());
        List<String> criteriaList = controller.getCriteria();

        if (criteriaList.isEmpty()) {
            selectingCriteria.add(noData());
        } else {
            checkBoxPanel.setLayout(new MigLayout("wrap"));
            checkBoxPanel.putClientProperty(FlatClientProperties.STYLE, ""
                    + "background:lighten(@background,7%)");

            for (String criteria : criteriaList) {
                cbCriteria = new JCheckBox(criteria);
                checkBoxPanel.add(cbCriteria, "h 30!");
                cbCriteriaList.add(cbCriteria);
                if (!criteriaSelectedList.isEmpty()) {
                    if (criteriaSelectedList.contains(cbCriteria.getText())) {
                        cbCriteria.setSelected(true);
                    }
                }
            }
            lbTitle = new JLabel("Pilih Kriteria");
            initStyle();
            scrollPane.putClientProperty(FlatClientProperties.STYLE, ""
                    + "border:0,0,0,0");
            scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                    + "trackArc:999;"
                    + "width:10");
            scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            scrollPane.setViewportView(checkBoxPanel);
            scrollPane.getViewport().setPreferredSize(new Dimension(300, 120));
            btnConfirm = new ButtonCustom(
                    "Hitung",
                    null,
                    "#d80009",
                    (e) -> {
                        if (validationInputCriteria()) {
                            changeContent(new SPKCountView(criteriaSelectedList, alternativeSelectedList));
                            SPKRankView.categoryID = categoryId;
                        } else {
                            controller.notificationCriteriaNotSelected();
                        }

                    });
            selectingCriteria.add(lbTitle, "w 110!, center");
            selectingCriteria.add(checkBoxPanel);
            selectingCriteria.add(btnConfirm, "gapy 5, h 30!");
        }
        recentPanel = selectingAlternative;
    }

    private void changeContent(JPanel panel) {
        removeAll();
        setLayout(new MigLayout("fill,wrap, insets 0", "[fill]", "[fill]"));
        add(panel);
        refreshUI();
    }

    private void addPanel(JPanel panel) {
        mainPanel.removeAll();
        IconCustom iconBack = new IconCustom("svg/back.svg", 1f, "#000000");
        btnBack = new ButtonCustom(
                null,
                iconBack.getIcon(),
                "#f2f2f2",
                (e) -> {

                    if (indexPanel == 0) {
                        changeContent(new SPKView());
                    } else {
                        mainPanel.remove(panel);
                        mainPanel.add(recentPanel, "center, gapy 30");
                        refreshUI();
                    }
                    indexPanel--;
                });
        mainPanel.add(btnBack);
        mainPanel.add(panel, "center, gapy 30");
        add(mainPanel);
        refreshUI();
    }

    private JPanel noData() {
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 0", "center,250:280", "center"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background, 7%)");
        JLabel lbNotaData = new JLabel("Tidak ada data!");
        lbNotaData.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +10");

        panel.add(lbNotaData);

        return panel;

    }

    boolean validationInputCriteria() {
        criteriaSelectedList.clear();
        for (JCheckBox checkbox : cbCriteriaList) {
            if (checkbox.isSelected()) {
                criteriaSelectedList.add(checkbox.getText());
            }
        }
        if (!criteriaSelectedList.isEmpty()) {
            return true;
        }

        return false;
    }

    boolean validationInputAlternative() {
        alternativeSelectedList.clear();
        for (JCheckBox checkbox : cbAlternativeList) {
            if (checkbox.isSelected()) {
                alternativeSelectedList.add(checkbox.getText());
            }
        }
        if (!alternativeSelectedList.isEmpty()) {
            return true;
        }
        return false;
    }

    private void refreshUI() {
        repaint();
        revalidate();
    }

    private int indexPanel = 0;
    private JPanel mainPanel;
    private ButtonCustom btnBack;
    private ButtonCustom btnConfirm;
    private JPanel recentPanel;
    private JPanel selectingCategory;
    private JPanel selectingCriteria;
    private JPanel selectingAlternative;
    private JComboBox<String> cbCategory;
    private JLabel lbTitle;
    private SPKController controller = new SPKController();
    private String categoryId;
    private JCheckBox cbAlternative;
    private JCheckBox cbCriteria;
    private List<JCheckBox> cbAlternativeList = new ArrayList<>();
    private List<JCheckBox> cbCriteriaList = new ArrayList<>();
    List<String> alternativeSelectedList = new ArrayList<>();
    List<String> criteriaSelectedList = new ArrayList<>();
}

package ac.id.unindra.hikal_spk.spk.view;

import java.awt.Component;

import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.text.PlainDocument;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.spk.controller.SPKController;
import ac.id.unindra.hikal_spk.spk.util.SAWAlgorithm;
import ac.id.unindra.hikal_spk.utils.DoubleFilter;
import ac.id.unindra.hikal_spk.utils.model.SPKModel;
import net.miginfocom.swing.MigLayout;

public class SPKCountView extends JPanel {

    public SPKCountView(List<String> criteriaSelectedList, List<String> alternativeSelectedList) {
        this.criteriaSelectedList = criteriaSelectedList;
        this.alternativeSelectedList = alternativeSelectedList;
        initComponent();

    }

    private void initLayout() {
        setLayout(new MigLayout("fill,wrap, insets 0 5 0 0", "[fill]", "[fill]"));
        mainPanel.setLayout(new MigLayout("wrap,insets 10", "[fill]", "[fill]"));
        alternativeListPanel.setLayout(new MigLayout("wrap"));
    }

    private void initStyle() {
        mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:lighten(@background,7%)");
        alternativeListPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%)");
        header.putClientProperty(FlatClientProperties.STYLE, "" +
                "font: bold +1");

    }

    private void initAdd() {
        mainPanel.add(alternativeListPanel(), "split 2, grow, top");
        mainPanel.add(criteriaListPanel(), "grow, top, gapx 20");
        mainPanel.add(countSPK(), "wrap, w 150!, h 30! , gapy 10");
        add(mainPanel);
    }

    private void initComponent() {
        initLayout();
        initAdd();
        initStyle();

    }

    JPanel alternativeListPanel() {
        header = new JLabel("Nama Alternatif");
        alternativeListPanel.add(header, "gapy 0 -5");
        for (String alternative : alternativeSelectedList) {
            lbAlternative = new JLabel(alternative);
            lbAlternative.putClientProperty(FlatClientProperties.STYLE, "" +
                    "font: 14");
            alternativeListPanel.add(lbAlternative, "gapy 23");
        }

        return alternativeListPanel;
    }

    JPanel criteriaListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("wrap, insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%)");

        JPanel headerPanel = createHeaderPanel();
        panel.add(headerPanel, "wrap");

        for (int i = 0; i < alternativeSelectedList.size(); i++) {
            JPanel panelField = createPanelFieldForAlternative();
            panel.add(panelField, "wrap, gapy 10");
            panelList.add(panelField);
        }

        return panel;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new MigLayout());
        headerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%)");

        for (String criteria : criteriaSelectedList) {
            Map<Object, Object> criteriaType = new LinkedHashMap<>();
            criteriaType.put("criteriaName", criteria);
            model.setCriteriaName(criteria);
            controller.getCriteriaWeight(model);
            criteriaType.put("criteriaType", model.getCriteriaType());
            JLabel lbCriteria = new JLabel(criteria);
            lbCriteria.putClientProperty(FlatClientProperties.STYLE, "font: bold +1");
            headerPanel.add(lbCriteria, "w 150!");
            criteriaTypeMap.add(criteriaType);
        }
        return headerPanel;
    }

    private JPanel createPanelFieldForAlternative() {
        JPanel panelField = new JPanel(new MigLayout("insets 0"));
        panelField.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,7%)");
        for (int i = 0; i < criteriaSelectedList.size(); i++) {
            panelField.add(rating(), "w 150!, h 30!");
        }
        return panelField;
    }

    ButtonCustom countSPK() {
        return btnCount = new ButtonCustom(
                "Hitung SPK",
                null,
                "#d80009",
                (e) -> {

                    if (validasiComponent()) {

                        getInput();
                        SAWAlgorithm spk = new SAWAlgorithm(
                                inputList,
                                criteriaTypeMap);
                        changeContent(new SPKRankView());

                    } else {
                        JOptionPane.showMessageDialog(null, "Pastikan semua terisi", "Peringatan",
                                JOptionPane.WARNING_MESSAGE);
                    }
                });

    }

    private void refreshUI() {
        repaint();
        revalidate();
    }

    boolean validasiComponent() {
        for (JPanel panel : panelList) {// jumlah alternatif
            for (Component comp : panel.getComponents()) {// jumlah kriteria
                if (comp instanceof JTextField) {
                    if (((JTextField) comp).getText().isEmpty()) {
                        return false;
                    }
                } else if (comp instanceof JComboBox) {
                    if (((JComboBox<?>) comp).getSelectedItem() == null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void getInput() {
        int indexCriteria = 0;
        int indexAlternative = 0;
        for (JPanel panel : panelList) {// jumlah alternatif
            Map<Object, Object> input = new LinkedHashMap<>();
            input.put("alternativeName", alternativeSelectedList.get(indexAlternative));
            for (Component comp : panel.getComponents()) {// jumlah kriteria

                double value = Double.parseDouble(((JTextField) comp).getText());
                input.put(criteriaSelectedList.get(indexCriteria), value);
                indexCriteria++;
            }
            indexCriteria = 0;
            indexAlternative++;
            inputList.add(input);
        }
    }

    TextFieldCustom rating() {
        txtCost = new TextFieldCustom("Masukan Bobot", null, true);
        PlainDocument doc = (PlainDocument) txtCost.getDocument();
        doc.setDocumentFilter(new DoubleFilter());
        return txtCost;
    }

    private void changeContent(JPanel panel) {
        removeAll();
        setLayout(new MigLayout("fill,wrap, insets 0", "[fill]", "[fill]"));
        add(panel);
        refreshUI();
    }

    // data dari selectedView
    private List<String> criteriaSelectedList = new ArrayList<>();
    private List<String> alternativeSelectedList = new ArrayList<>();
    private TextFieldCustom txtCost;
    private JPanel mainPanel = new JPanel();
    JPanel alternativeListPanel = new JPanel();
    JCheckBox cbCriteria;
    JCheckBox cbAlternative;
    JLabel header;
    JLabel lbAlternative;
    private SPKModel model = new SPKModel();
    boolean isRefresh = true;
    ButtonCustom btnCount;
    List<JPanel> panelList = new ArrayList<>();
    List<Map<Object, Object>> inputList = new ArrayList<>();
    List<Map<Object, Object>> criteriaTypeMap = new ArrayList<>();
    SPKController controller = new SPKController();

}
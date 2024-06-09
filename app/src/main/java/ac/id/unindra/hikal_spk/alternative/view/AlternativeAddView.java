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
import ac.id.unindra.hikal_spk.alternative.controller.AlternativeController;
import ac.id.unindra.hikal_spk.category.view.CategoryView;
import ac.id.unindra.hikal_spk.utils.model.AlternativeModel;
import ac.id.unindra.hikal_spk.utils.model.CategoryModel;
import net.miginfocom.swing.MigLayout;

public class AlternativeAddView extends JPanel {

    public AlternativeAddView(String title) {
        this.title = title;
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
        cbCategory.putClientProperty(FlatClientProperties.STYLE, ""
                + "focusColor:#e7000a;"
                + "focusedBorderColor:#e7000a");
        cbCategory.setRenderer(new DefaultListCellRenderer() {
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
        lbTitle = new JLabel(title);
        JLabel lbCriteriaName = new JLabel("Nama Alternatif");
        JLabel lbCriteriaAlternative = new JLabel("Kategori Alternatif");
        txtAlternativeName = new TextFieldCustom(
                "Masukan Nama Alternatif",
                null,
                true);

        String[] categoryArray = new String[controller.getCategory().size()];
        int i = 0;
        for (String category : controller.getCategory()) {
            categoryArray[i++] = category;
        }

        cbCategory = new JComboBox<>(categoryArray);
        btnSubmit = new ButtonCustom(
                "Submit",
                null,
                "#e7000a",
                (e) -> {
                    submit();
                });

        contentPanel.add(lbTitle, "w 190!, center");
        contentPanel.add(lbCriteriaName, "gapy 20");
        contentPanel.add(txtAlternativeName, "gapy 0, h 35!");
        contentPanel.add(lbCriteriaAlternative, "gapy 10");
        contentPanel.add(cbCategory, "gapy 0, h 35!");
        contentPanel.add(btnSubmit, "gapy 10, h 35!");
    }

    private void changeContent(JPanel panel) {
        removeAll();
        setLayout(new MigLayout("fill,wrap, insets 0", "[fill]", "[fill]"));
        add(panel);
        refreshUI();
    }

    private void refreshUI() {
        repaint();
        revalidate();
    }

    public void setAlternativeEdit(AlternativeModel model) {
        oldAlternativeName = model.getAlternativeName();
        oldCategoryName = model.getCategory().getCategoryName();
        txtAlternativeName.setText(oldAlternativeName);
        cbCategory.setSelectedItem(oldCategoryName);

        this.model = model;
    }

    private void submit() {
        controller.getInput(model, txtAlternativeName, cbCategory);
        if (controller.fieldNotEmpty(txtAlternativeName)) {
            if (isUpdate) {
                handleUpdate();
            } else {
                handleCreation();
            }

        }
    }

    private void handleCreation() {
        if (controller.isRegistered(model)) {
            controller.notificationIsnAvail();
        } else {
            controller.createCategory(model);
            changeContent(new AlternativeView());
        }
    }

    private void handleUpdate() {
        boolean alternativeSame = oldAlternativeName.equalsIgnoreCase(txtAlternativeName.getText());
        boolean categorySame = oldCategoryName.equalsIgnoreCase(cbCategory.getSelectedItem().toString());
        boolean isSame = alternativeSame && categorySame;
        if (isSame) {
            controller.notificationIsnChange();
        } else {
            if (controller.isRegistered(model)) {
                controller.notificationIsnAvail();
            } else {
                controller.updateData(model);
                changeContent(new AlternativeView());
            }

        }
    }

    public static boolean isUpdate;
    private String oldCategoryName;
    private String oldAlternativeName;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private TextFieldCustom txtAlternativeName;
    private ButtonCustom btnSubmit;
    private ButtonCustom btnBack;
    private JComboBox<String> cbCategory;
    private AlternativeController controller = new AlternativeController();
    private AlternativeModel model = new AlternativeModel();
    private JLabel lbTitle;
    private String title;
}

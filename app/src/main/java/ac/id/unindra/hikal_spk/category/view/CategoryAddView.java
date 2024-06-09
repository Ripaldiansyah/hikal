package ac.id.unindra.hikal_spk.category.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.category.controller.CategoryController;
import ac.id.unindra.hikal_spk.utils.model.CategoryModel;
import net.miginfocom.swing.MigLayout;

public class CategoryAddView extends JPanel {

    public CategoryAddView(String title) {
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
    }

    private void initAdd() {
        IconCustom iconBack = new IconCustom("svg/back.svg", 1f, "#000000");
        btnBack = new ButtonCustom(
                null,
                iconBack.getIcon(),
                "#f2f2f2",
                (e) -> {
                    changeContent(new CategoryView());
                });
        mainPanel.add(btnBack);
        mainPanel.add(contentPanel, "center, gapy 30");
        add(mainPanel);
    }

    private void setContent() {
        lbTitle = new JLabel(title);
        JLabel lbCategoryName = new JLabel("Nama kategori");
        txtCategoryName = new TextFieldCustom(
                "Masukan Nama Kategori",
                null,
                true);
        btnSubmit = new ButtonCustom(
                "Submit",
                null,
                "#e7000a",
                (e) -> {
                    submit();
                });

        contentPanel.add(lbTitle, "w 177!, center");
        contentPanel.add(lbCategoryName, "gapy 20");
        contentPanel.add(txtCategoryName, "gapy 0, h 35!");
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

    public void setTextCategoryEdit(CategoryModel model) {
        txtCategoryName.setText(model.getCategoryName());
        this.model = model;
    }

    private void submit() {
        controller.getInput(model, txtCategoryName);
        if (controller.fieldNotEmpty(txtCategoryName)) {

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
            changeContent(new CategoryView());
        }

    }

    private void handleUpdate() {
        if (oldCategoryName.equalsIgnoreCase(txtCategoryName.getText())) {
            controller.notificationIsnChange();
        } else {
            if (controller.isRegistered(model)) {
                controller.notificationIsnAvail();
            } else {
                controller.updateData(model);
                changeContent(new CategoryView());
            }

        }
    }

    public static boolean isUpdate;
    public static String oldCategoryName;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private TextFieldCustom txtCategoryName;
    private ButtonCustom btnSubmit;
    private ButtonCustom btnBack;
    private JLabel lbTitle;
    private String title;
    private CategoryModel model = new CategoryModel();
    private CategoryController controller = new CategoryController();
}

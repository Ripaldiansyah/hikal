package ac.id.unindra.hikal_spk.spk.view;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import com.formdev.flatlaf.FlatClientProperties;

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.UI.Table.TableCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.spk.controller.SPKController;
import ac.id.unindra.hikal_spk.utils.CurrentDateTime;
import ac.id.unindra.hikal_spk.utils.UserToken;
import ac.id.unindra.hikal_spk.utils.model.CategoryModel;
import ac.id.unindra.hikal_spk.utils.model.SPKModel;
import ac.id.unindra.hikal_spk.utils.model.UserModel;
import ac.id.unindra.hikal_spk.utils.model.TableModel.RankTableModel;
import net.miginfocom.swing.MigLayout;

public class SPKRankView extends JPanel {

    public SPKRankView() {
        initComponent();
    }

    private void initComponent() {
        initLayout();
        setHeader();
        setContent();
        initAdd();
        initStyle();
        handleSave();
    }

    private void initLayout() {
        setLayout(new MigLayout("fill,wrap, insets 0 5 0 0", "[fill]", "[fill]"));
        mainPanel = new JPanel(new MigLayout("fill,wrap, insets 10 10 0 10", "[fill,left]", "[top]"));
        headerPanel = new JPanel(new MigLayout("fill,wrap, insets 5", "[fill,left]", "[top]"));
        contentPanel = new JPanel(new MigLayout("fill,wrap, insets 0", "[fill, center]", "[fill,top]"));

    }

    private void initStyle() {
        mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:lighten(@background,10%)");
        headerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,10%)");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +10");
    }

    private void initAdd() {
        mainPanel.add(headerPanel);
        mainPanel.add(contentPanel, "push");
        add(mainPanel);

    }

    private void setHeader() {
        lbTitle = new JLabel("Hasil SPK");
        IconCustom iconPrint = new IconCustom("svg/print.svg", 1f, null);
        btnPrint = new ButtonCustom(
                "Cetak",
                iconPrint.getIcon(),
                "#e7000a",
                (e) -> {

                });
        headerPanel.add(lbTitle, "split 3");
        headerPanel.add(btnPrint, "w 100!, h 30!");

    }

    private void setContent() {
        tableModel.setData(rankListMap);
        spkTable = new TableCustom(tableModel, null);
        contentPanel.add(spkTable);
    }

    private void refreshUI() {
        repaint();
        revalidate();
    }

    private void saveSpk() {
        UserModel user = new UserModel();
        CategoryModel category = new CategoryModel();
        user.setUserID(UserToken.userId);
        category.setCategoryID(categoryID);
        model.setUser(user);
        model.setCategory(category);
        model.setCreatedAt(CurrentDateTime.getDateTime());
        model.setRankListMap(rankListMap);
        controller.saveSPK(model);

    }

    private void handleSave() {
        if (!isOnlyView) {
            saveSpk();
        }

    }

    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel contentPanel;
    private JLabel lbTitle;
    public static List<Map<Object, Object>> rankListMap;
    public static String categoryID;
    public static boolean isOnlyView = false;
    private ButtonCustom btnPrint;
    private TableCustom spkTable;
    private SPKController controller = new SPKController();
    private RankTableModel tableModel = new RankTableModel();
    SPKModel model = new SPKModel();
}

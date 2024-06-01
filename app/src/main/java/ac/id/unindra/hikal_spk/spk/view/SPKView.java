package ac.id.unindra.hikal_spk.spk.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import net.miginfocom.swing.MigLayout;

public class SPKView extends JPanel {

        public SPKView() {
                initComponent();
        }

        private void initComponent() {
                initLayout();
                setHeader();
                setFooter();
                initStyle();
                initAdd();

        }

        private void initLayout() {
                setLayout(new MigLayout("fill,wrap, insets 0 5 0 0", "[fill]", "[fill]"));
                mainPanel = new JPanel(new MigLayout("fill,wrap, insets 10 10 0 10", "[fill,left]", "[top]"));
                headerPanel = new JPanel(new MigLayout("fill,wrap, insets 5", "[fill,left]", "[top]"));
                contentPanel = new JPanel(new MigLayout("fill,wrap, insets 5", "[fill, center]", "[fill,top]"));
                footerPanel = new JPanel(new MigLayout("insets 5, wrap", "[left]", "[top]"));
        }

        private void initStyle() {
                mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20;"
                                + "background:lighten(@background,10%)");
                headerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:lighten(@background,10%)");
                contentPanel.putClientProperty(FlatClientProperties.STYLE, ""

                                + "background:#b8afe2");
                footerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:lighten(@background,10%)");
                lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold +10");
        }

        private void initAdd() {
                mainPanel.add(headerPanel);
                mainPanel.add(contentPanel);
                mainPanel.add(footerPanel);
                add(mainPanel);

        }

        private void setHeader() {
                lbTitle = new JLabel("SPK");
                txtSearch = new TextFieldCustom(
                                "Cari Keputusan",
                                null,
                                true);
                IconCustom iconPrint = new IconCustom("svg/print.svg", 1f, null);
                btnPrint = new ButtonCustom(
                                "Cetak",
                                iconPrint.getIcon(),
                                "#a0a0a0",
                                (e) -> {

                                });
                IconCustom iconAdd = new IconCustom("svg/add.svg", 1f, null);
                btnAdd = new ButtonCustom(
                                "Keputusan Baru",
                                iconAdd.getIcon(),
                                "#e7000a",
                                (e) -> {

                                });
                headerPanel.add(lbTitle, "split 3");
                headerPanel.add(btnPrint, "w 100!, h 30!");
                headerPanel.add(btnAdd, "w 140!, h 30!, gapx 10");
                headerPanel.add(txtSearch, "h 30!, gapy 10");
        }

        private void setFooter() {
                IconCustom iconDelete = new IconCustom("svg/delete.svg", 1f, null);
                btnDelete = new ButtonCustom(
                                "Hapus Keputusan",
                                iconDelete.getIcon(),
                                "#e7000a",
                                (e) -> {

                                });
                IconCustom iconInfo = new IconCustom("svg/info.svg", 1f, null);
                btnInfo = new ButtonCustom(
                                "Lihat Keputusan",
                                iconInfo.getIcon(),
                                "#a0a0a0",
                                (e) -> {

                                });

                footerPanel.add(btnDelete, "w 140!, h 30!");
                footerPanel.add(btnInfo, "w 140!, h 30!,gapy 5");

        }

        JPanel mainPanel;
        JPanel headerPanel;
        JPanel contentPanel;
        JPanel footerPanel;
        JLabel lbTitle;
        TextFieldCustom txtSearch;
        ButtonCustom btnAdd;
        ButtonCustom btnPrint;
        ButtonCustom btnDelete;
        ButtonCustom btnInfo;

}

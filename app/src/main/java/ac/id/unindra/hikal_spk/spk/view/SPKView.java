package ac.id.unindra.hikal_spk.spk.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.event.MouseInputAdapter;
import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.UI.Icon.IconCustom;
import ac.id.unindra.hikal_spk.UI.Table.TableCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.report.controller.ReportController;
import ac.id.unindra.hikal_spk.spk.controller.SPKController;
import ac.id.unindra.hikal_spk.utils.UserToken;
import ac.id.unindra.hikal_spk.utils.model.SPKModel;
import ac.id.unindra.hikal_spk.utils.model.TableModel.SPKTableModel;
import net.miginfocom.swing.MigLayout;

public class SPKView extends JPanel {

        public SPKView() {
                initComponent();
        }

        private void initComponent() {
                initLayout();
                setHeader();
                setContent();
                setFooter();
                disableButton();
                initStyle();
                initAdd();

        }

        private void initLayout() {
                setLayout(new MigLayout("fill,wrap, insets 0 5 0 0", "[fill]", "[fill]"));
                mainPanel = new JPanel(new MigLayout("fill,wrap, insets 10 10 0 10", "[fill,left]", "[top]"));
                headerPanel = new JPanel(new MigLayout("fill,wrap, insets 5", "[fill,left]", "[top]"));
                contentPanel = new JPanel(new MigLayout("fill,wrap, insets 0", "[fill, center]", "[fill,top]"));
                footerPanel = new JPanel(new MigLayout("insets 5, wrap", "[left]", "[top]"));
        }

        private void initStyle() {
                mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "arc:20;"
                                + "background:lighten(@background,10%)");
                headerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:lighten(@background,10%)");
                footerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "background:lighten(@background,10%)");
                lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:bold +10");
        }

        private void initAdd() {
                mainPanel.add(headerPanel);
                mainPanel.add(contentPanel);
                mainPanel.add(footerPanel, "push");
                add(mainPanel);

        }

        private void setHeader() {
                lbTitle = new JLabel("SPK");
                txtSearch = new TextFieldCustom(
                                "Cari Keputusan",
                                null,
                                true);
                txtSearch.addKeyListener(new KeyListener() {

                        @Override
                        public void keyTyped(KeyEvent e) {
                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                        }

                        @Override
                        public void keyReleased(KeyEvent e) {
                                spkTableModel.setData(controller.searchData(txtSearch.getText(), UserToken.userId));
                        }

                });
                IconCustom iconPrint = new IconCustom("svg/print.svg", 1f, null);
                btnPrint = new ButtonCustom(
                                "Cetak",
                                iconPrint.getIcon(),
                                "#a0a0a0",
                                (e) -> {
                                        ReportController reportController = new ReportController();
                                        reportController.ReportSPKAll();
                                });
                IconCustom iconAdd = new IconCustom("svg/add.svg", 1f, null);
                btnAdd = new ButtonCustom(
                                "Keputusan Baru",
                                iconAdd.getIcon(),
                                "#e7000a",
                                (e) -> {
                                        changeContent(new SPKSelectView());
                                });
                headerPanel.add(lbTitle, "split 3");
                headerPanel.add(btnPrint, "w 100!, h 30!");
                headerPanel.add(btnAdd, "w 140!, h 30!, gapx 10");
                headerPanel.add(txtSearch, "h 30!, gapy 10");
        }

        private void setContent() {
                spkTableModel.setData(controller.getData(UserToken.userId));
                spkTable = new TableCustom(spkTableModel, null);
                spkTable.getTable().addMouseListener(new MouseInputAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                indexRow = spkTable.getSelectedRow();
                                enableButton();
                        }
                });
                contentPanel.add(spkTable);
        }

        private void setFooter() {
                IconCustom iconDelete = new IconCustom("svg/delete.svg", 1f, null);
                btnDelete = new ButtonCustom(
                                "Hapus Keputusan",
                                iconDelete.getIcon(),
                                "#e7000a",
                                (e) -> {
                                        int index = spkTable.getSelectedRow();
                                        if (index != -1) {
                                                controller.deleteData(spkTableModel, indexRow);
                                                disableButton();
                                        }
                                });
                IconCustom iconInfo = new IconCustom("svg/info.svg", 1f, null);
                btnInfo = new ButtonCustom(
                                "Lihat Keputusan",
                                iconInfo.getIcon(),
                                "#a0a0a0",
                                (e) -> {
                                        getDetailRank();
                                });

                footerPanel.add(btnDelete, "w 140!, h 30!");
                footerPanel.add(btnInfo, "w 140!, h 30!,gapy 5");

        }

        private void changeContent(JPanel panel) {
                removeAll();
                setLayout(new MigLayout("fill,wrap, insets 0", "[fill]", "[fill]"));
                add(panel);
                refreshUI();
        }

        private void getDetailRank() {
                model.setSpkID(spkTableModel.getSelectedIndex(indexRow).getSpkID());
                SPKRankView.rankListMap = controller.getDetailRank(model);
                SPKRankView.isOnlyView = true;
                SPKRankView.idSPK = model.getSpkID();
                changeContent(new SPKRankView());
        }

        private void refreshUI() {
                repaint();
                revalidate();
        }

        private void disableButton() {
                btnDelete.setEnabled(false);
                btnInfo.setEnabled(false);
        }

        private void enableButton() {
                btnDelete.setEnabled(true);
                btnInfo.setEnabled(true);
        }

        private int indexRow;
        private JPanel mainPanel;
        private JPanel headerPanel;
        private JPanel contentPanel;
        private JPanel footerPanel;
        private JLabel lbTitle;
        private TextFieldCustom txtSearch;
        private ButtonCustom btnAdd;
        private ButtonCustom btnPrint;
        private ButtonCustom btnDelete;
        private ButtonCustom btnInfo;
        private SPKTableModel spkTableModel = new SPKTableModel();
        private TableCustom spkTable;
        private SPKController controller = new SPKController();
        private SPKModel model = new SPKModel();

}

package ac.id.unindra.hikal_spk.report.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.hikal_spk.UI.Button.ButtonCustom;
import ac.id.unindra.hikal_spk.report.controller.ReportController;
import net.miginfocom.swing.MigLayout;

public class ReportView extends JPanel {

    public ReportView() {
        initComponent();
    }

    private void initComponent() {
        initLayout();
        setHeader();
        setContent();
        initAdd();
        initStyle();
    }

    private void initLayout() {
        setLayout(new MigLayout("fill,wrap, insets 0 5 0 0", "[fill]", "[fill]"));
        mainPanel = new JPanel(new MigLayout("fill,wrap, insets 10 10 0 10", "[fill,left]", "[top]"));
        headerPanel = new JPanel(new MigLayout("fill,wrap, insets 5", "[fill,left]", "[top]"));
        contentPanel = new JPanel(new MigLayout("fill,wrap, insets 5", "[fill, left]", "[fill,top]"));
    }

    private void initStyle() {
        mainPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "background:lighten(@background,10%)");
        headerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,10%)");
        contentPanel.putClientProperty(FlatClientProperties.STYLE, ""
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
        lbTitle = new JLabel("Laporan");
        headerPanel.add(lbTitle);

    }

    private void setContent() {
        JComboBox<String> cbReport = new JComboBox<>(reportCategory);
        contentPanel.add(cbReport, "w 250!, h 30!");
        cbReport.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = (int) cbReport.getSelectedIndex();
                if (index != 0) {
                    if (btnPrint != null) {
                        contentPanel.remove(btnPrint);
                    }

                    btnPrint = new ButtonCustom(
                            "Cetak",
                            null,
                            "#d80009",
                            (ae) -> {
                                switch (index) {
                                    case 1:
                                        controller.ReportUser();
                                        break;
                                    case 2:
                                        controller.ReportCriteria();
                                        break;
                                    case 3:
                                        controller.ReportCategory();
                                        break;
                                    case 4:
                                        controller.ReportAlternative();
                                        break;
                                    case 5:
                                        controller.ReportSPKAll();
                                        break;
                                }
                            });

                    contentPanel.add(btnPrint, "w 250!, h 30!, gapy 5");

                } else {
                    if (btnPrint != null) {
                        contentPanel.remove(btnPrint);
                    }
                }

                refreshUI();
            }
        });
    }

    private void refreshUI() {
        revalidate();
        repaint();
    }

    private ReportController controller = new ReportController();
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel contentPanel;
    private JLabel lbTitle;
    private ButtonCustom btnPrint;
    String[] reportCategory = {
            "Pilih Laporan",
            "Laporan Pengguna",
            "Laporan Kriteria",
            "Laporan Kategori",
            "Laporan Alternatif",
            "Laporan SPK" };
}

package ac.id.unindra.hikal_spk.report.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JOptionPane;

import ac.id.unindra.hikal_spk.utils.DatabaseConnection;
import ac.id.unindra.hikal_spk.utils.model.ReportModel;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ReportController {

    public ReportController() {
        conn = new DatabaseConnection().connect();
    }

    public void ReportAlternative() {

        try {
            String file = "app/src/main/resources/jasper/ReportAlternative.jasper";
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(JRParameter.REPORT_LOCALE, new Locale("id"));

            JasperPrint print = JasperFillManager.fillReport(file, parameters, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    public void ReportCriteria() {

        try {
            String file = "app/src/main/resources/jasper/ReportCriteria.jasper";
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(JRParameter.REPORT_LOCALE, new Locale("id"));

            JasperPrint print = JasperFillManager.fillReport(file, parameters, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    public void ReportUser() {

        try {
            String file = "app/src/main/resources/jasper/ReportUser.jasper";
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(JRParameter.REPORT_LOCALE, new Locale("id"));

            JasperPrint print = JasperFillManager.fillReport(file, parameters, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    public void ReportCategory() {

        try {
            String file = "app/src/main/resources/jasper/ReportCategory.jasper";
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(JRParameter.REPORT_LOCALE, new Locale("id"));

            JasperPrint print = JasperFillManager.fillReport(file, parameters, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    public void ReportSPKAll() {

        try {
            String file = "app/src/main/resources/jasper/ReportSPKAll.jasper";
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(JRParameter.REPORT_LOCALE, new Locale("id"));

            JasperPrint print = JasperFillManager.fillReport(file, parameters, conn);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    public void ReportSPKResult() {

        try {
            String file = "app/src/main/resources/jasper/ReportSPKResult.jasper";
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put(JRParameter.REPORT_LOCALE, new Locale("id"));

            List<ReportModel> alternativeRanks = new ArrayList<>();
            for (Map<Object, Object> map : rankListMap) {
                String alternativeName = (String) map.get("alternativeName");
                int rank = (int) map.get("rank");
                alternativeRanks.add(new ReportModel(alternativeName, rank));
            }

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(alternativeRanks);
            JasperPrint print = JasperFillManager.fillReport(file, parameters, dataSource);
            JasperViewer.viewReport(print, false);

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    private Connection conn;
    public static List<Map<Object, Object>> rankListMap = new ArrayList<>();

}

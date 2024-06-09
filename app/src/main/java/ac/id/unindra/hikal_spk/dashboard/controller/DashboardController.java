package ac.id.unindra.hikal_spk.dashboard.controller;

import ac.id.unindra.hikal_spk.utils.dao.DashboardDAO;

public class DashboardController {
    private DashboardDAO DAO;

    public DashboardController() {
        DAO = new DashboardDAO();
    }

    public String getCountUser() {
        return String.valueOf(DAO.getCountUser());
    }

    public String getCountCategory() {
        return String.valueOf(DAO.getCountCategory());
    }

    public String getCountAlternative() {
        return String.valueOf(DAO.getCountAlternative());
    }

    public String getCountCriteria() {
        return String.valueOf(DAO.getCountCriteria());
    }

    public String getCountSpk() {
        return String.valueOf(DAO.getCountSPK());
    }
}

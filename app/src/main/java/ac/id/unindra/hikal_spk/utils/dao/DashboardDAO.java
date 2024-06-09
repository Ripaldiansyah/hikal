package ac.id.unindra.hikal_spk.utils.dao;

import ac.id.unindra.hikal_spk.utils.DatabaseConnection;
import ac.id.unindra.hikal_spk.utils.service.DashboardService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAO implements DashboardService {
    private Connection conn;

    public DashboardDAO() {
        conn = new DatabaseConnection().connect();
    }

    @Override
    public int getCountUser() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        int count = 0;
        String sql = "SELECT COUNT(*) AS count FROM users";
        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            if (rs.next()) {
                return count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }

    @Override
    public int getCountCategory() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        int count = 0;
        String sql = "SELECT COUNT(*) AS count FROM category";
        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            if (rs.next()) {
                return count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }

    @Override
    public int getCountCriteria() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        int count = 0;
        String sql = "SELECT COUNT(*) AS count FROM criteria";
        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            if (rs.next()) {
                return count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }

    @Override
    public int getCountAlternative() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        int count = 0;
        String sql = "SELECT COUNT(*) AS count FROM alternative";
        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            if (rs.next()) {
                return count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }

    @Override
    public int getCountSPK() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        int count = 0;
        String sql = "SELECT COUNT(*) AS count FROM spk";
        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            if (rs.next()) {
                return count = rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }
}

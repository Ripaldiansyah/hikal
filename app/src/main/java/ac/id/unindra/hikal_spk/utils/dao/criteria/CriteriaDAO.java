package ac.id.unindra.hikal_spk.utils.dao.criteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ac.id.unindra.hikal_spk.utils.DatabaseConnection;
import ac.id.unindra.hikal_spk.utils.model.category.CategoryModel;
import ac.id.unindra.hikal_spk.utils.model.criteria.CriteriaModel;
import ac.id.unindra.hikal_spk.utils.service.criteria.CriteriaService;;

public class CriteriaDAO implements CriteriaService {

    public CriteriaDAO() {
        conn = new DatabaseConnection().connect();
    }

    @Override
    public boolean isRegistered(CriteriaModel criteria) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM criteria WHERE criteria_name = ?";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, criteria.getCriteriaName());
            rs = stat.executeQuery();
            if (!rs.next()) {
                return false;
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
        return true;
    }

    @Override
    public void createCriteria(CriteriaModel criteria) {
        PreparedStatement stat = null;
        String sql = "INSERT INTO criteria (criteria_name, criteria_weight, criteria_type ) VALUES (?,?,?)";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, criteria.getCriteriaName());
            stat.setDouble(2, criteria.getCriteriaWeight());
            stat.setString(3, criteria.getCriteriaType());
            stat.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateCriteria(CriteriaModel criteria) {
        PreparedStatement stat = null;
        String sql = "UPDATE criteria SET criteria_name=?, criteria_weight=?, criteria_type=? WHERE criteria_id=?";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, criteria.getCriteriaName());
            stat.setDouble(2, criteria.getCriteriaWeight());
            stat.setString(3, criteria.getCriteriaType());
            stat.setString(4, criteria.getCriteraID());
            stat.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteCriteria(CriteriaModel criteria) {
        PreparedStatement stat = null;
        String sql = "DELETE From criteria where criteria_id=?";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, criteria.getCriteraID());
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<CriteriaModel> getCriteria() {
        PreparedStatement stat = null;
        List<CriteriaModel> criteriaList = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM criteria ";

        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                CriteriaModel criteria = new CriteriaModel();
                criteria.setCriteraID(rs.getString("criteria_id"));
                criteria.setCriteriaName(rs.getString("criteria_name"));
                criteria.setCriteriaType(rs.getString("criteria_type"));
                criteria.setCriteriaWeight(rs.getDouble("criteria_weight"));

                criteriaList.add(criteria);

            }
            return criteriaList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
    }

    @Override
    public List<CriteriaModel> searchCriteria(String key) {
        PreparedStatement stat = null;
        List<CriteriaModel> criteriaList = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM criteria "
                + "WHERE criteria_id LIKE '%" + key + "%' "
                + "OR criteria_name LIKE '%" + key + "%' "
                + "OR criteria_type LIKE '%" + key + "%' "
                + "OR criteria_weight LIKE '%" + key + "%' ";

        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();

            while (rs.next()) {
                CriteriaModel criteria = new CriteriaModel();
                criteria.setCriteraID(rs.getString("criteria_id"));
                criteria.setCriteriaName(rs.getString("criteria_name"));
                criteria.setCriteriaType(rs.getString("criteria_type"));
                criteria.setCriteriaWeight(rs.getDouble("criteria_weight"));
                criteriaList.add(criteria);
            }

            return criteriaList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
    }

    private Connection conn;

}

package ac.id.unindra.hikal_spk.utils.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ac.id.unindra.hikal_spk.utils.DatabaseConnection;
import ac.id.unindra.hikal_spk.utils.model.CategoryModel;
import ac.id.unindra.hikal_spk.utils.model.SPKModel;
import ac.id.unindra.hikal_spk.utils.model.UserModel;
import ac.id.unindra.hikal_spk.utils.service.SPKService;

public class SPKDAO implements SPKService {

    private Connection conn;
    private int SPKid;

    public SPKDAO() {
        conn = new DatabaseConnection().connect();
    }

    @Override
    public void createSPK(SPKModel spk) {
        PreparedStatement stat = null;
        String sql = "INSERT INTO spk (category_id, user_id, spk_created_at) VALUES (?,?,?)";
        try {
            stat = conn.prepareStatement(sql, new String[] { "id_spk" });
            stat.setString(1, spk.getCategory().getCategoryID());
            stat.setString(2, spk.getUser().getUserID());
            stat.setString(3, spk.getCreatedAt());
            stat.executeUpdate();

            ResultSet generatedKeys = stat.getGeneratedKeys();

            if (generatedKeys.next()) {
                SPKid = generatedKeys.getInt(1);
                spk.setSpkID(String.valueOf(SPKid));
                detailSPK(spk);
            }

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
    public void detailSPK(SPKModel spk) {
        PreparedStatement stat = null;
        String sql = "INSERT INTO spk_detail (id_spk, alternative_name, alternative_rank) VALUES (?,?,?)";
        try {
            stat = conn.prepareStatement(sql);

            for (Map<Object, Object> rank : spk.getRankListMap()) {

                stat.setString(1, spk.getSpkID());
                stat.setString(2, rank.get("alternativeName").toString());
                stat.setString(3, rank.get("rank").toString());
                stat.executeUpdate();

            }

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
    public void deleteSPK(SPKModel spk) {
        PreparedStatement stat = null;
        String sqlSPK = "DELETE From spk WHERE spk_id=?";
        String sqlSPKDetail = "DELETE From spk_detail WHERE id_spk=?";

        try {
            stat = conn.prepareStatement(sqlSPKDetail);
            stat.setString(1, spk.getSpkID());
            stat.executeUpdate();

            stat = conn.prepareStatement(sqlSPK);
            stat.setString(1, spk.getSpkID());
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
    public List<SPKModel> getSPK(String userID) {
        PreparedStatement stat = null;
        List<SPKModel> spkList = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT spk.spk_id, "
                + "spk.category_id, "
                + "spk.spk_created_at, "
                + "spk.user_id, "
                + "ct.category_name, "
                + "usr.fullname "
                + "FROM spk spk "
                + "INNER JOIN category ct "
                + "ON spk.category_id = ct.category_id "
                + "INNER JOIN users usr "
                + "ON spk.user_id = usr.user_id "
                + "WHERE spk.user_id = ?";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, userID);
            rs = stat.executeQuery();
            while (rs.next()) {
                SPKModel spk = new SPKModel();
                CategoryModel category = new CategoryModel();
                spk.setSpkID(rs.getString("spk_id"));
                category.setCategoryID(rs.getString("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                spk.setCreatedAt(rs.getString("spk_created_at"));
                spk.setCategory(category);
                spkList.add(spk);

            }
            return spkList;
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
    public List<SPKModel> searchSPK(String key, String userId) {
        PreparedStatement stat = null;
        List<SPKModel> spkList = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT spk.spk_id, "
                + "spk.category_id, "
                + "spk.spk_created_at, "
                + "spk.user_id, "
                + "ct.category_name "
                + "FROM spk spk "
                + "INNER JOIN category ct "
                + "ON spk.category_id = ct.category_id "
                + "WHERE (spk.category_id LIKE ? "
                + "OR spk_id LIKE ? "
                + "OR spk_created_at LIKE ? "
                + "OR ct.category_name LIKE ?) "
                + "AND spk.user_id = ?";

        try {
            stat = conn.prepareStatement(sql);
            String searchKey = "%" + key + "%";
            stat.setString(1, searchKey);
            stat.setString(2, searchKey);
            stat.setString(3, searchKey);
            stat.setString(4, searchKey);
            stat.setString(5, userId);

            rs = stat.executeQuery();
            while (rs.next()) {
                SPKModel spk = new SPKModel();
                CategoryModel category = new CategoryModel();
                UserModel user = new UserModel();
                spk.setSpkID(rs.getString("spk_id"));
                category.setCategoryID(rs.getString("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                user.setUserID(rs.getString("user_id"));
                spk.setCreatedAt(rs.getString("spk_created_at"));
                spk.setCategory(category);
                spk.setUser(user);
                spkList.add(spk);
            }

            return spkList;
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
    public List<String> getAlternative(String categoryID) {
        PreparedStatement stat = null;
        List<String> criteria = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT alt.alternative_name, "
                + "alt.category_id, "
                + "ct.category_name "
                + "FROM alternative alt "
                + "INNER JOIN category ct "
                + "ON alt.category_id = ct.category_id "
                + "WHERE alt.category_id = ?";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, categoryID);
            rs = stat.executeQuery();
            while (rs.next()) {
                String value = rs.getString("alternative_name");
                criteria.add(value);
            }
            return criteria;
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
    public String getCategoryID(String categoryName) {
        PreparedStatement stat = null;
        String categoryID;
        ResultSet rs = null;
        String sql = "SELECT category_id FROM category WHERE category_name = ?";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, categoryName);
            rs = stat.executeQuery();
            if (rs.next()) {
                categoryID = rs.getString("category_id");
                return categoryID;
            }
            return null;

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
    public List<String> getCriteria() {
        PreparedStatement stat = null;
        List<String> criteria = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM Criteria";

        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                String value = rs.getString("criteria_name");
                criteria.add(value);
            }
            return criteria;
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
    public double getCriteriaWeight(SPKModel spk) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM criteria WHERE criteria_name = ?";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, spk.getCriteriaName());
            rs = stat.executeQuery();
            if (rs.next()) {
                spk.setCriteriaType(rs.getString("criteria_type"));
                return rs.getDouble("criteria_weight");
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
        return 0;
    }

    @Override
    public List<Map<Object, Object>> getDetailRank(SPKModel spk) {
        PreparedStatement stat = null;
        List<Map<Object, Object>> rankListMap = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM spk_detail WHERE id_spk = ?; ";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, spk.getSpkID());
            rs = stat.executeQuery();
            while (rs.next()) {
                String alternativeName = rs.getString("alternative_name");
                int rank = rs.getInt("alternative_rank");

                Map<Object, Object> rankMap = new LinkedHashMap<>();
                rankMap.put("alternativeName", alternativeName);
                rankMap.put("rank", rank);
                rankListMap.add(rankMap);
            }
            return rankListMap;
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
}

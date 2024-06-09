package ac.id.unindra.hikal_spk.utils.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ac.id.unindra.hikal_spk.utils.DatabaseConnection;
import ac.id.unindra.hikal_spk.utils.model.AlternativeModel;
import ac.id.unindra.hikal_spk.utils.model.CategoryModel;
import ac.id.unindra.hikal_spk.utils.service.AlternativeService;

public class AlternativeDAO implements AlternativeService {

    public AlternativeDAO() {
        conn = new DatabaseConnection().connect();
    }

    @Override
    public boolean isRegistered(AlternativeModel alternative) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM alternative WHERE alternative_name = ? AND category_id = ?";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, alternative.getAlternativeName());
            stat.setString(2, alternative.getCategory().getCategoryID());
            rs = stat.executeQuery();
            if (!rs.next()) {
                return false;
            } else {
                String id = rs.getString("alternative_id");
                alternative.setAlternativeID(id);
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
    public void createAlternative(AlternativeModel alternative) {
        PreparedStatement stat = null;
        String sql = "INSERT INTO alternative (category_id ,alternative_name) VALUES (?,?)";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, alternative.getCategory().getCategoryID());
            stat.setString(2, alternative.getAlternativeName());
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
    public void updateAlternative(AlternativeModel alternative) {
        PreparedStatement stat = null;
        String sql = "UPDATE alternative SET category_id=?, alternative_name=? WHERE alternative_id=?";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, alternative.getCategory().getCategoryID());
            stat.setString(2, alternative.getAlternativeName());
            stat.setString(3, alternative.getAlternativeID());
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
    public void deleteAlternative(AlternativeModel alternative) {
        PreparedStatement stat = null;
        String sql = "DELETE From alternative where alternative_id=?";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, alternative.getAlternativeID());
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
    public List<AlternativeModel> getAlternative() {
        PreparedStatement stat = null;
        List<AlternativeModel> alternativeList = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT alt.alternative_id, "
                + "alt.category_id, "
                + "alt.alternative_name, "
                + "ct.category_name "
                + "FROM alternative alt "
                + "INNER JOIN category ct "
                + "ON alt.category_id = ct.category_id";

        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                AlternativeModel alternative = new AlternativeModel();
                CategoryModel category = new CategoryModel();
                alternative.setAlternativeID(rs.getString("alternative_id"));
                category.setCategoryID(rs.getString("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                alternative.setAlternativeName(rs.getString("alternative_name"));
                alternative.setCategory(category);
                alternativeList.add(alternative);

            }
            return alternativeList;
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
    public List<AlternativeModel> searchAlternative(String key) {
        PreparedStatement stat = null;
        List<AlternativeModel> alternativeList = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT alt.alternative_id, "
                + "alt.category_id, "
                + "alt.alternative_name, "
                + "ct.category_name "
                + "FROM alternative alt "
                + "INNER JOIN category ct "
                + "ON alt.category_id = ct.category_id "
                + "WHERE (alternative_id LIKE '%" + key + "%') "
                + "OR (alternative_name LIKE '%" + key + "%') "
                + "OR (ct.category_name LIKE '%" + key + "%') ";
        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();

            while (rs.next()) {
                AlternativeModel alternative = new AlternativeModel();
                CategoryModel category = new CategoryModel();
                alternative.setAlternativeID(rs.getString("alternative_id"));
                alternative.setAlternativeName(rs.getString("alternative_name"));
                category.setCategoryID(rs.getString("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                alternative.setCategory(category);
                alternativeList.add(alternative);
            }

            return alternativeList;
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
    public List<String> getCategory() {
        PreparedStatement stat = null;
        List<String> category = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM category ";

        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                category.add(rs.getString("category_name"));
            }
            return category;
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

    @Override
    public void setIDCategory(AlternativeModel alternative) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT category_id FROM category WHERE category_name = ?";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, alternative.getCategory().getCategoryName());
            rs = stat.executeQuery();
            if (rs.next()) {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setCategoryID(rs.getString("category_id"));
                alternative.setCategory(categoryModel);
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

    }
}

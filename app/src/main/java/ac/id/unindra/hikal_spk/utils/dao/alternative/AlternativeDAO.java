package ac.id.unindra.hikal_spk.utils.dao.alternative;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ac.id.unindra.hikal_spk.utils.DatabaseConnection;
import ac.id.unindra.hikal_spk.utils.model.alternative.AlternativeModel;
import ac.id.unindra.hikal_spk.utils.model.category.CategoryModel;
import ac.id.unindra.hikal_spk.utils.service.alternative.AlternativeService;

public class AlternativeDAO implements AlternativeService {

    public AlternativeDAO() {
        conn = new DatabaseConnection().connect();
    }

    @Override
    public boolean isRegistered(AlternativeModel alternative) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM alternative WHERE alternative_name = ? AND categoryID = ?";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, alternative.getAlternativeName());
            stat.setString(2, alternative.getCategory().getCategoryID());
            rs = stat.executeQuery();
            if (!rs.next()) {
                return false;
            } else {
                String id = rs.getString("AlternativeID");
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
        String sql = "INSERT INTO alternative (categoryID ,alternative_name) VALUES (?,?)";
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
        String sql = "UPDATE alternative SET categoryID=?, alternative_name=? WHERE alternativeID=?";
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
        String sql = "DELETE From alternative where alternativeID=?";
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
        List alternativeList = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT alt.alternativeID, "
                + "alt.categoryID, "
                + "alt.alternative_name, "
                + "ct.category_name "
                + "FROM alternative alt "
                + "INNER JOIN category ct "
                + "ON alt.categoryID = ct.categoryID";

        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                AlternativeModel alternative = new AlternativeModel();
                CategoryModel category = new CategoryModel();
                alternative.setAlternativeID(rs.getString("alternativeID"));
                category.setCategoryID(rs.getString("categoryID"));
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
        List alternativeList = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT alt.alternativeID, "
                + "alt.categoryID, "
                + "alt.alternative_name, "
                + "ct.category_name "
                + "FROM alternative alt "
                + "INNER JOIN category ct "
                + "ON alt.categoryID = ct.categoryID "
                + "WHERE (alternativeID LIKE '%" + key + "%') "
                + "OR (alternative_name LIKE '%" + key + "%') "
                + "OR (ct.category_name LIKE '%" + key + "%') ";
        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();

            while (rs.next()) {
                AlternativeModel alternative = new AlternativeModel();
                CategoryModel category = new CategoryModel();
                alternative.setAlternativeID(rs.getString("alternativeID"));
                alternative.setAlternativeName(rs.getString("alternative_name"));
                category.setCategoryID(rs.getString("categoryID"));
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
        String sql = "SELECT categoryID FROM category WHERE category_name = ?";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, alternative.getCategory().getCategoryName());
            rs = stat.executeQuery();
            if (rs.next()) {
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setCategoryID(rs.getString("categoryID"));
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

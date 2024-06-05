package ac.id.unindra.hikal_spk.utils.dao.category;

import java.util.List;

import ac.id.unindra.hikal_spk.utils.DatabaseConnection;
import ac.id.unindra.hikal_spk.utils.model.category.CategoryModel;
import ac.id.unindra.hikal_spk.utils.service.category.CategoryService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO implements CategoryService {

    public CategoryDAO() {
        conn = new DatabaseConnection().connect();
    }

    @Override
    public boolean isRegistered(CategoryModel model) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM category WHERE category_name = ?";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, model.getCategoryName());
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
    public void createCategory(CategoryModel model) {
        PreparedStatement stat = null;
        String sql = "INSERT INTO category (category_name) VALUES (?)";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, model.getCategoryName());
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
    public void updateCategory(CategoryModel model) {
        PreparedStatement stat = null;
        String sql = "UPDATE category SET category_name=? WHERE category_id=?";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, model.getCategoryName());
            stat.setString(2, model.getCategoryID());
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
    public void deleteCategory(CategoryModel model) {
        PreparedStatement stat = null;
        String sql = "DELETE From category where category_id=?";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, model.getCategoryID());
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
    public List<CategoryModel> getCategory() {
        PreparedStatement stat = null;
        List<CategoryModel> categoryList = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM category ";

        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                CategoryModel category = new CategoryModel();
                category.setCategoryID(rs.getString("category_id"));
                category.setCategoryName(rs.getString("category_name"));

                categoryList.add(category);

            }
            return categoryList;
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
    public List<CategoryModel> searchCategory(String key) {
        PreparedStatement stat = null;
        List<CategoryModel> categoryList = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM category " +
                "WHERE category_id LIKE '%" + key + "%' " +
                "OR category_name LIKE '%" + key + "%' ";

        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();

            while (rs.next()) {
                CategoryModel category = new CategoryModel();
                category.setCategoryID(rs.getString("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                categoryList.add(category);
            }

            return categoryList;
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

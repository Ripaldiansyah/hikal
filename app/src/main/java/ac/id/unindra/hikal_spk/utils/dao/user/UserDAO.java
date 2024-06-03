package ac.id.unindra.hikal_spk.utils.dao.user;

import ac.id.unindra.hikal_spk.dashboard.view.DashboardView;
import ac.id.unindra.hikal_spk.menu.view.MenuView;
import ac.id.unindra.hikal_spk.utils.DatabaseConnection;
import ac.id.unindra.hikal_spk.utils.model.user.UserModel;
import ac.id.unindra.hikal_spk.utils.service.user.UserService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class UserDAO implements UserService {

    public UserDAO() {
        conn = new DatabaseConnection().connect();
    }

    @Override
    public boolean isRegistered(UserModel model) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE username = ? AND Password = ?";

        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, model.getUsername());
            stat.setString(2, model.getPassword());
            rs = stat.executeQuery();
            if (!rs.next()) {
                return false;
            } else {
                String id = rs.getString("user_id");
                String role = rs.getString("role");
                String fullname = rs.getString("fullname");

                DashboardView.fullname = fullname;
                if (role.equalsIgnoreCase("admin")) {
                    MenuView.isAdmin = true;
                } else {
                    MenuView.isAdmin = false;
                }
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
    public boolean usernameIsAvailable(UserModel model) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) AS count FROM users WHERE username = ?;";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, model.getUsername());
            rs = stat.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    return false;
                } else {
                    return true;
                }
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
        return false;
    }

    @Override
    public void createUser(UserModel model) {
        PreparedStatement stat = null;
        String sql = "INSERT INTO users (fullname, username, gender, password,role) VALUES (?,?,?,?,?)";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, model.getFullname());
            stat.setString(2, model.getUsername());
            stat.setString(3, model.getGender());
            stat.setString(4, model.getPassword());
            stat.setString(5, model.getRole());
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
    public void updateUser(UserModel model) {
        PreparedStatement stat = null;
        String sql = "UPDATE users SET fullname=?, gender=? , password=?, role=? WHERE user_id=?";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, model.getFullname());
            stat.setString(2, model.getGender());
            stat.setString(3, model.getPassword());
            stat.setString(4, model.getRole());
            stat.setString(5, model.getUserID());
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
    public void deleteUser(UserModel model) {
        PreparedStatement stat = null;
        String sql = "DELETE From users where user_id=?";
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1, model.getUserID());
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
    public List<UserModel> getUser() {
        PreparedStatement stat = null;
        List users = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM users ";

        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            while (rs.next()) {
                UserModel user = new UserModel();
                user.setUserID(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setGender(rs.getString("gender"));
                user.setRole(rs.getString("role"));
                users.add(user);

            }
            return users;
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
    public List<UserModel> searchUser(String key) {
        PreparedStatement stat = null;
        List users = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM users " +
                "WHERE user_id LIKE '%" + key + "%' " +
                "OR full_name LIKE '%" + key + "%' " +
                "OR username LIKE '%" + key + "%' " +
                "OR gender LIKE '%" + key + "%' " +
                "OR role LIKE '%" + key + "%' ";

        try {
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();

            while (rs.next()) {
                UserModel user = new UserModel();
                user.setUserID(rs.getString("user_id"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("full_name"));
                user.setGender(rs.getString("gender"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }

            return users;
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

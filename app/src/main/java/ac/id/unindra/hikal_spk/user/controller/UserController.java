package ac.id.unindra.hikal_spk.user.controller;

import java.util.List;

import ac.id.unindra.hikal_spk.utils.dao.user.UserDAO;
import ac.id.unindra.hikal_spk.utils.model.user.UserModel;

public class UserController {
    private UserDAO user;
    private UserModel model = new UserModel();

    public UserController() {
        this.user = new UserDAO();
    }

    public String[] tableHeader() {
        return model.getColumnHeader();
    }

    public List<UserModel> getData() {
        return user.getUser();
    }

    public List<UserModel> searchData(String key) {
        return user.searchUser(key);
    }

    public void deleteData(UserModel model) {
        user.deleteUser(model);
    }

}

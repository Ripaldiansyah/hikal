package ac.id.unindra.hikal_spk.login.controller;

import ac.id.unindra.hikal_spk.utils.dao.user.UserDAO;
import ac.id.unindra.hikal_spk.utils.model.user.UserModel;

public class LoginController {
    private UserDAO login;

    public LoginController() {
        this.login = new UserDAO();
    }

    public boolean isRegistered(UserModel model) {
        return login.isRegistered(model);
    }
}

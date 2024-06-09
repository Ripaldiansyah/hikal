package ac.id.unindra.hikal_spk.login.controller;

import ac.id.unindra.hikal_spk.utils.dao.UserDAO;
import ac.id.unindra.hikal_spk.utils.model.UserModel;

public class LoginController {
    private UserDAO login;

    public LoginController() {
        this.login = new UserDAO();
    }

    public boolean isRegistered(UserModel model) {
        return login.isRegistered(model);
    }
}

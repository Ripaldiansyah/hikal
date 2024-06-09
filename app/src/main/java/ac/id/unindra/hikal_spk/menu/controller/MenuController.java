package ac.id.unindra.hikal_spk.menu.controller;

import javax.swing.JPanel;

import ac.id.unindra.hikal_spk.Main;
import ac.id.unindra.hikal_spk.alternative.view.AlternativeView;
import ac.id.unindra.hikal_spk.category.view.CategoryView;
import ac.id.unindra.hikal_spk.criteria.view.CriteriaView;
import ac.id.unindra.hikal_spk.dashboard.view.DashboardView;
import ac.id.unindra.hikal_spk.login.view.LoginView;
import ac.id.unindra.hikal_spk.register.view.RegisterView;
import ac.id.unindra.hikal_spk.report.view.ReportView;
import ac.id.unindra.hikal_spk.spk.view.SPKView;
import ac.id.unindra.hikal_spk.user.view.UserView;
import ac.id.unindra.hikal_spk.utils.UserToken;
import ac.id.unindra.hikal_spk.utils.model.UserModel;

public class MenuController {

    public void logout() {
        Main.content.loginRegisterPanel(new LoginView());
    }

    public JPanel dashboard() {
        return new DashboardView();
    }

    public JPanel alternative() {
        return new AlternativeView();
    }

    public JPanel criteria() {
        return new CriteriaView();
    }

    public JPanel spk() {
        return new SPKView();
    }

    public JPanel user() {
        return new UserView();
    }

    public JPanel category() {
        return new CategoryView();
    }

    public JPanel report() {
        return new ReportView();
    }

    public JPanel setting() {
        UserModel model = new UserModel();
        model.setUserID(UserToken.userId);
        model.setUsername(UserToken.username);
        model.setFullname(UserToken.fullname);
        model.setGender(UserToken.gender);
        model.setRole(UserToken.role);
        RegisterView.isFromLogin = false;
        RegisterView.isUpdateUser = true;
        RegisterView.isFromSetting = true;
        RegisterView.oldUsername = UserToken.username;
        RegisterView editUser = new RegisterView("Ubah");
        editUser.setTextUserEdit(model);
        return editUser;
    }

}

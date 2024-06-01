package ac.id.unindra.hikal_spk.menu.controller;

import javax.swing.JPanel;

import ac.id.unindra.hikal_spk.Main;
import ac.id.unindra.hikal_spk.alternative.view.AlternativeView;
import ac.id.unindra.hikal_spk.category.view.CategoryView;
import ac.id.unindra.hikal_spk.criteria.view.CriteriaView;
import ac.id.unindra.hikal_spk.dashboard.view.DashboardView;
import ac.id.unindra.hikal_spk.login.view.LoginView;
import ac.id.unindra.hikal_spk.spk.view.SPKView;
import ac.id.unindra.hikal_spk.user.view.UserView;

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

}

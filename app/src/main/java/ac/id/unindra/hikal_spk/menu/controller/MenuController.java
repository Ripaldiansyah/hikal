package ac.id.unindra.hikal_spk.menu.controller;

import javax.swing.JPanel;

import ac.id.unindra.hikal_spk.Main;
import ac.id.unindra.hikal_spk.alternative.view.AlternativeView;
import ac.id.unindra.hikal_spk.criteria.view.CriteriaView;
import ac.id.unindra.hikal_spk.login.view.LoginView;

public class MenuController {

    public void logout() {
        Main.content.loginRegisterPanel(new LoginView());
    }

    public JPanel alternative() {
        return new AlternativeView();
    }

    public JPanel criteria() {
        return new CriteriaView();
    }

}

package ac.id.unindra.hikal_spk.register.controller;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import ac.id.unindra.hikal_spk.UI.PasswordField.PasswordFieldCustom;
import ac.id.unindra.hikal_spk.UI.TextField.TextFieldCustom;
import ac.id.unindra.hikal_spk.utils.MD5;
import ac.id.unindra.hikal_spk.utils.dao.user.UserDAO;
import ac.id.unindra.hikal_spk.utils.model.user.UserModel;

public class RegisterController {
    private UserDAO user;

    public RegisterController() {
        this.user = new UserDAO();
    }

    public void createUser(UserModel model) {
        user.createUser(model);
    }

    public boolean usernameIsAvailable(UserModel model) {
        return user.usernameIsAvailable(model);
    }

    public void updateUser(UserModel model) {
        user.updateUser(model);
    }

    // ui controller

    public boolean fieldNotEmpty(TextFieldCustom[] component) {
        for (TextFieldCustom field : component) {
            if (field.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pastikan semua telah terisi",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public boolean passwordValidation(PasswordFieldCustom[] component) {

        for (PasswordFieldCustom field : component) {
            if (field.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Pastikan semua telah terisi",
                        "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        if (!component[0].getText().equals(component[1].getText())) {
            JOptionPane.showMessageDialog(null, "Periksa kembali password Anda",
                    "Password tidak sesuai",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void getInput(UserModel model, TextFieldCustom txtFullname, TextFieldCustom txtUsername,
            JRadioButton genderMale, PasswordFieldCustom txtPassword, JComboBox<String> cbRole) {
        String fullname = txtFullname.getText();
        String username = txtUsername.getText().toLowerCase();
        String gender = (genderMale.isSelected()) ? "Laki - Laki " : "Perempuan";
        String password = MD5.getMD5(txtPassword.getText());
        String role = (String) cbRole.getSelectedItem();
        model.setFullname(fullname);
        model.setUsername(username);
        model.setGender(gender);
        model.setPassword(password);
        model.setRole(role);
    }

    public void notificationUpdate() {
        JOptionPane.showMessageDialog(null,
                "Akun Berhasil diubah",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void notificationCreate() {
        JOptionPane.showMessageDialog(null,
                "Akun Berhasil dibuat",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void notificationIsnAvail() {
        JOptionPane.showMessageDialog(null,
                "Masukan nama pengguna lain",
                "Nama Pengguna telah terdaftar",
                JOptionPane.ERROR_MESSAGE);
    }
}

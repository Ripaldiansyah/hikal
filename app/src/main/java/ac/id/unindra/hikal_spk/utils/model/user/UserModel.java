package ac.id.unindra.hikal_spk.utils.model.user;

public class UserModel {

    String UserID;
    String fullname;
    String gender;
    String username;
    String password;
    String role;
    String key;

    private final String[] columnHeader = {
            "ID Pengguna",
            "Nama Lengkap",
            "Nama Pengguna",
            "Gender",
            "Role",
    };

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getColumnHeader() {
        return columnHeader;
    }

}

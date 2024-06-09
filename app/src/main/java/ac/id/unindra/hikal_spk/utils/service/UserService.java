package ac.id.unindra.hikal_spk.utils.service;

import java.util.List;

import ac.id.unindra.hikal_spk.utils.model.UserModel;

public interface UserService {

    boolean isRegistered(UserModel model);

    boolean usernameIsAvailable(UserModel model);

    void createUser(UserModel model);

    void updateUser(UserModel model);

    void deleteUser(UserModel model);

    List<UserModel> getUser();

    List<UserModel> searchUser(String key);

}

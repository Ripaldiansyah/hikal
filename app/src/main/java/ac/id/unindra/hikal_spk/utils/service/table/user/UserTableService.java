package ac.id.unindra.hikal_spk.utils.service.table.user;

import java.util.List;

import ac.id.unindra.hikal_spk.utils.model.user.UserModel;

public interface UserTableService {
    void setData(List<UserModel> user);

    void removeData(int index);

    void clear();

    UserModel getSelectedIndex(int index);
}

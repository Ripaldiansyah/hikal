package ac.id.unindra.hikal_spk.utils.service.table.alternative;

import java.util.List;

import ac.id.unindra.hikal_spk.utils.model.alternative.AlternativeModel;

public interface AlternativeTableService {
    void setData(List<AlternativeModel> user);

    void removeData(int index);

    void clear();

    AlternativeModel getSelectedIndex(int index);
}

package ac.id.unindra.hikal_spk.utils.service.tableService;

import java.util.List;

import ac.id.unindra.hikal_spk.utils.model.AlternativeModel;

public interface AlternativeTableService {
    void setData(List<AlternativeModel> alternative);

    void removeData(int index);

    void clear();

    AlternativeModel getSelectedIndex(int index);
}

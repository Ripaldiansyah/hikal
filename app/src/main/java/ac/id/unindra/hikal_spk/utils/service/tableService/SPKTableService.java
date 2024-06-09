package ac.id.unindra.hikal_spk.utils.service.tableService;

import java.util.List;

import ac.id.unindra.hikal_spk.utils.model.SPKModel;

public interface SPKTableService {
    void setData(List<SPKModel> alternative);

    void removeData(int index);

    void clear();

    SPKModel getSelectedIndex(int index);
}

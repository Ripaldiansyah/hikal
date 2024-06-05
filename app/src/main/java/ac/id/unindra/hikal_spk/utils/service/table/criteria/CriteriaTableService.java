package ac.id.unindra.hikal_spk.utils.service.table.criteria;

import java.util.List;

import ac.id.unindra.hikal_spk.utils.model.criteria.CriteriaModel;

public interface CriteriaTableService {

    void setData(List<CriteriaModel> criteria);

    void removeData(int index);

    void clear();

    CriteriaModel getSelectedIndex(int index);
}

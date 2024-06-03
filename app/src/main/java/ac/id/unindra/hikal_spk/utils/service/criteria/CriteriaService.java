package ac.id.unindra.hikal_spk.utils.service.criteria;

import ac.id.unindra.hikal_spk.utils.model.criteria.CriteriaModel;
import java.util.List;

public interface CriteriaService {

    boolean isRegistered(CriteriaModel criteria);

    void createCriteria(CriteriaModel criteria);

    void updateCriteria(CriteriaModel criteria);

    void deleteCriteria(CriteriaModel criteria);

    List<CriteriaModel> getCriteria();

    List<CriteriaModel> searchCriteria(String key);
}

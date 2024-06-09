package ac.id.unindra.hikal_spk.utils.service;

import java.util.List;

import ac.id.unindra.hikal_spk.utils.model.CriteriaModel;

public interface CriteriaService {

    boolean isRegistered(CriteriaModel criteria);

    void createCriteria(CriteriaModel criteria);

    void updateCriteria(CriteriaModel criteria);

    void deleteCriteria(CriteriaModel criteria);

    List<CriteriaModel> getCriteria();

    List<CriteriaModel> searchCriteria(String key);
}

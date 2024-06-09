package ac.id.unindra.hikal_spk.utils.service;

import java.util.List;
import java.util.Map;

import ac.id.unindra.hikal_spk.utils.model.SPKModel;

public interface SPKService {
    void createSPK(SPKModel spk);

    void detailSPK(SPKModel spk);

    void deleteSPK(SPKModel spk);

    List<SPKModel> getSPK(String userID);

    List<SPKModel> searchSPK(String key, String userID);

    List<String> getAlternative(String categoryID);

    List<String> getCriteria();

    String getCategoryID(String categoryName);

    double getCriteriaWeight(SPKModel spk);

    List<Map<Object, Object>> getDetailRank(SPKModel spk);
}

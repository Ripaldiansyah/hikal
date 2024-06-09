package ac.id.unindra.hikal_spk.utils.service;

import java.util.List;

import ac.id.unindra.hikal_spk.utils.model.AlternativeModel;

public interface AlternativeService {

    boolean isRegistered(AlternativeModel alternative);

    void setIDCategory(AlternativeModel alternative);

    void createAlternative(AlternativeModel alternative);

    void updateAlternative(AlternativeModel alternative);

    void deleteAlternative(AlternativeModel alternative);

    List<AlternativeModel> getAlternative();

    List<AlternativeModel> searchAlternative(String key);

    List<String> getCategory();
}

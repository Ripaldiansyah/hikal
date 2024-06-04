package ac.id.unindra.hikal_spk.utils.service.alternative;

import ac.id.unindra.hikal_spk.utils.model.alternative.AlternativeModel;
import java.util.List;

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

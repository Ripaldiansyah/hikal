package ac.id.unindra.hikal_spk.utils.service.tableService;

import java.util.List;
import java.util.Map;

public interface RankTableService {
    void setData(List<Map<Object, Object>> rank);

    void clear();
}
package ac.id.unindra.hikal_spk.utils.model.TableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import ac.id.unindra.hikal_spk.spk.controller.SPKController;
import ac.id.unindra.hikal_spk.utils.service.tableService.RankTableService;

public class RankTableModel extends AbstractTableModel implements RankTableService {
    List<Map<Object, Object>> rankListMap = new ArrayList<>();
    SPKController controller = new SPKController();

    @Override
    public int getRowCount() {
        return rankListMap.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Map<Object, Object> rowData = rankListMap.get(rowIndex);
        Object[] keys = rowData.keySet().toArray();
        Object key = keys[columnIndex];
        return rowData.get(key);
    }

    @Override
    public String getColumnName(int column) {
        String[] header = {
                "Nama Alternatif",
                "Peringkat"
        };
        return header[column];
    }

    @Override
    public void setData(List<Map<Object, Object>> rank) {
        clear();
        rankListMap = rank;
        fireTableDataChanged();
    }

    @Override
    public void clear() {
        rankListMap.clear();
        fireTableDataChanged();
    }

}

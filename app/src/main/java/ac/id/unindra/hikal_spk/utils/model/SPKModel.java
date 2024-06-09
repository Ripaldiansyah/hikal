package ac.id.unindra.hikal_spk.utils.model;

import java.util.List;
import java.util.Map;

public class SPKModel {
    List<Map<Object, Object>> rankListMap;
    String spkID;
    CategoryModel category;
    UserModel user;
    String createdAt;
    String criteriaName;
    String criteriaType;
    private final String[] columnHeader = {
            "ID Keputusan",
            "Kategori",
            "Tanggal Perhitungan",
    };

    public List<Map<Object, Object>> getRankListMap() {
        return rankListMap;
    }

    public void setRankListMap(List<Map<Object, Object>> rankListMap) {
        this.rankListMap = rankListMap;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getSpkID() {
        return spkID;
    }

    public void setSpkID(String spkID) {
        this.spkID = spkID;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String[] getColumnHeader() {
        return columnHeader;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public String getCriteriaType() {
        return criteriaType;
    }

    public void setCriteriaType(String criteriaType) {
        this.criteriaType = criteriaType;
    }

}

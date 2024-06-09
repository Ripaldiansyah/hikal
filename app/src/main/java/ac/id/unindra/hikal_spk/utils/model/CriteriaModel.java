package ac.id.unindra.hikal_spk.utils.model;

public class CriteriaModel {
    String criteraID;
    String criteriaName;
    String criteriaType;
    double criteriaWeight;
    private final String[] columnHeader = {
            "ID Kriteria",
            "Nama Kriteria",
            "Bobot Kriteria",
            "Jenis Kriteria",
    };

    public String[] getColumnHeader() {
        return columnHeader;
    }

    public String getCriteraID() {
        return criteraID;
    }

    public void setCriteraID(String criteraID) {
        this.criteraID = criteraID;
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

    public double getCriteriaWeight() {
        return criteriaWeight;
    }

    public void setCriteriaWeight(double criteriaWeight) {
        this.criteriaWeight = criteriaWeight;
    }

}

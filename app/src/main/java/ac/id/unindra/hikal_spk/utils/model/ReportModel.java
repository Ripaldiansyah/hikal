package ac.id.unindra.hikal_spk.utils.model;

public class ReportModel {

    private String alternativeName;
    private int rank;

    public ReportModel(String alternativeName, int rank) {
        this.alternativeName = alternativeName;
        this.rank = rank;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}

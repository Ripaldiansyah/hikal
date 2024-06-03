package ac.id.unindra.hikal_spk.utils.model.alternative;

import ac.id.unindra.hikal_spk.utils.model.category.CategoryModel;

public class AlternativeModel {

    String alternativeID;
    String alternativeName;
    CategoryModel category;

    public String getAlternativeID() {
        return alternativeID;
    }

    public void setAlternativeID(String alternativeID) {
        this.alternativeID = alternativeID;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

}
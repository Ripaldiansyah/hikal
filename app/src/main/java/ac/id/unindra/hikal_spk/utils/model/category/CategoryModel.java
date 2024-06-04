package ac.id.unindra.hikal_spk.utils.model.category;

public class CategoryModel {
    String categoryID;
    String categoryName;
    private final String[] columnHeader = {
            "ID Kategori",
            "Nama Kategori",
    };

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String[] getColumnHeader() {
        return columnHeader;
    }

}

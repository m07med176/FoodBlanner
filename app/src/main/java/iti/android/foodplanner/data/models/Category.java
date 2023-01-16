package iti.android.foodplanner.data.models;

public class Category {
    private String strCategory;

    public Category() {
    }

    public Category(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "strCategory='" + strCategory + '\'' +
                '}';
    }
}

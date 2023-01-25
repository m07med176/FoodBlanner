package iti.android.foodplanner.data.models.selections.category;

public class Category {
    private String strCategory;

    public Category() {
    }

    public Category(String strCategory) {
        this.strCategory = strCategory;
    }


    public String getThumbail() {
        return  "https://www.themealdb.com/images/category/"+strCategory+".png";
    }


    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

}

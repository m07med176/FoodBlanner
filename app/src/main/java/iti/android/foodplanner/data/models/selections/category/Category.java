package iti.android.foodplanner.data.models.selections.category;

public class Category {
    private String strCategory;
    private String thumbail;

    public Category() {
    }

    public Category(String strCategory) {
        this.strCategory = strCategory;
        this.thumbail = thumbail = "https://www.themealdb.com/images/category/"+strCategory+".png";
    }


    public String getThumbail() {
        return thumbail;
    }

    public void setThumbail(String thumbail) {
        this.thumbail = "https://www.themealdb.com/images/category/"+strCategory+".png";
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

}

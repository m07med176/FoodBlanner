package iti.android.foodplanner.data.models.selections.categoryFeed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesFeed {

	@SerializedName("meals")
	private List<CategoriesItem> categories;

	public void setCategories(List<CategoriesItem> categories){
		this.categories = categories;
	}

	public List<CategoriesItem> getCategories(){
		return categories;
	}

	@Override
 	public String toString(){
		return 
			"Categories{" +
			"Category = '" + categories + '\'' +
			"}";
		}
}
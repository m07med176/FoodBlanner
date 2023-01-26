package iti.android.foodplanner.data.models.selections.category;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoriesList {

	@SerializedName("meals")
	private List<Category> categories = new ArrayList<>();

	public void setCategory(List<Category> categories){
		this.categories = categories;
	}

	public List<Category> getCategory(){
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
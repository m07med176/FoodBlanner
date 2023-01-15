package iti.android.foodplanner.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// TODO Moamen [DATA]: insert it as Entity
public class Meals{

	@SerializedName("meals")
	private List<MealsItem> meals;
	public void setMeals(List<MealsItem> meals){
		this.meals = meals;
	}

	public List<MealsItem> getMeals(){
		return meals;
	}

	@Override
 	public String toString(){
		return 
			"Meals{" + 
			"meals = '" + meals + '\'' + 
			"}";
		}
}
package iti.android.foodplanner.data.models.selections.Ingredient;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class IngredientsList {

	@SerializedName("meals")
	private List<Ingredient> meals = new ArrayList<>();

	public void setMeals(List<Ingredient> meals){
		this.meals = meals;
	}

	public List<Ingredient> getMeals(){
		return meals;
	}

	@Override
 	public String toString(){
		return 
			"Ingredients{" +
			"Ingredient = '" + meals + '\'' +
			"}";
		}
}
package iti.android.foodplanner.ui.features.details;

import iti.android.foodplanner.data.models.meal.MealPlan;
import iti.android.foodplanner.data.models.meal.MealsItem;

public interface DetailsInterface {

    public void addToPlan(MealPlan mealPlan);
    public void addToFav(MealsItem mealsItem);
    public void deleteFromPlan(MealPlan mealPlan);
    public void deleteFromFav(MealsItem mealsItem);

}

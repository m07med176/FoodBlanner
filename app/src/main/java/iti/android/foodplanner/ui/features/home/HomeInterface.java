package iti.android.foodplanner.ui.features.home;

import iti.android.foodplanner.data.models.meal.MealsItem;

public interface HomeInterface {
    public void onSavePlane(MealsItem item);
    public void onDeletePlane(MealsItem item);
    public void onSaveFavorite(MealsItem item);
    public void onDeleteFavorite(MealsItem item);
}

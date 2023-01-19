package iti.android.foodplanner.ui.features.home;

import iti.android.foodplanner.data.models.meal.MealsItem;

public interface HomeInterface {
    public void onSavePlane(MealsItem item);
    public void onSaveFavorite(MealsItem item);
    // TODO get daily inspiration from [network or cash from ROOM]
    // TODO get ArrayList of Categories Food
}

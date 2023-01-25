package iti.android.foodplanner.ui.features.search;

import java.util.List;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.models.meal.MealsItem;

public interface SearchInterface extends DataFetch<List<MealsItem>>{
    public void onSavePlane(MealsItem item);
    public void onSaveFavorite(MealsItem item);
}

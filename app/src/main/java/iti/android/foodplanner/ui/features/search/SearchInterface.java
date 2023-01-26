package iti.android.foodplanner.ui.features.search;

import java.util.List;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.models.meal.MealsItem;

public interface SearchInterface extends DataFetch<List<MealsItem>>{
    public static final int AREA = 1;
    public static final int INGREDIENT = 2;
    public static final int CATEGORY = 3;
    public static final int SEARCH = 4;
    public void onSavePlane(MealsItem item);
    public void onSaveFavorite(MealsItem item);
}

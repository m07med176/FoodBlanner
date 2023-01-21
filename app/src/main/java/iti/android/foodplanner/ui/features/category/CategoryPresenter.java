package iti.android.foodplanner.ui.features.category;

import android.content.Context;

import java.util.List;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealsItem;

public class CategoryPresenter {
    Repository repository;

    public CategoryPresenter(Context context) {
        repository = Repository.getInstance(context);
    }

    public void getCategories(DataFetch<List<MealsItem>> dataFetch){
        repository.retrieveFilterResults(null,null,"American",dataFetch);
    }
}

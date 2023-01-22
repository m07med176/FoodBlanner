package iti.android.foodplanner.ui.features.category;

import android.content.Context;

import java.util.List;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.data.models.selections.Ingredient.Ingredient;
import iti.android.foodplanner.data.models.selections.area.Area;
import iti.android.foodplanner.data.models.selections.category.Category;

public class CategoryPresenter {
    Repository repository;

    public CategoryPresenter(Context context) {
        repository = Repository.getInstance(context);
    }

    public void getCategories(DataFetch<List<MealsItem>> dataFetch){
        repository.retrieveFilterResults(null,null,"American",dataFetch);
    }

    public void getFilterAreaResults(DataFetch<List<Area>> dataFetch){
        repository.areasList(dataFetch);
    }

    public void getFilterCategoryResults(DataFetch<List<Category>> dataFetch){
        repository.categoriesList(dataFetch);
    }

    public void getFilterIngredientResults(DataFetch<List<Ingredient>> dataFetch){
        repository.ingredientsList(dataFetch);
    }



}

package iti.android.foodplanner.ui.features.search;

import android.content.Context;

import java.util.List;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.data.models.selections.Ingredient.Ingredient;
import iti.android.foodplanner.data.models.selections.area.Area;
import iti.android.foodplanner.data.models.selections.category.Category;

public class SearchPresenter {
    public static final String AREA= "AREA";
    public static final String CATEGORY= "CATEGORY";
    public static final String INGREDIENT= "INGREDIENT";
    public static final String SEARCH= "SEARCH";
    private Repository repository;
    private SearchInterface searchInterface;


    public SearchPresenter(Context context,SearchInterface searchInterface) {
        this.searchInterface = searchInterface;
        repository = Repository.getInstance(context);
    }

    public void getSearchResultMeals(String type,String query){
        switch (type){
            case AREA:
                repository.retrieveFilterResults(null, null, query,searchInterface);
                break;
            case CATEGORY:
                repository.retrieveFilterResults(query, null, null,searchInterface);
                break;
            case INGREDIENT:
                repository.retrieveFilterResults(null, query, null,searchInterface);
                break;
            case SEARCH:
                repository.searchMealsByName(query,searchInterface);
                break;


        }


    }

}

package iti.android.foodplanner.ui.features.search;

import android.content.Context;

import java.util.List;
import java.util.Random;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.data.models.selections.Ingredient.Ingredient;
import iti.android.foodplanner.data.models.selections.area.Area;
import iti.android.foodplanner.data.models.selections.category.Category;
import iti.android.foodplanner.data.shared.SharedManager;

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
    public String[] getCashList(int type){
        String[] result = {""};
        switch (type){
            case SearchInterface.SEARCH:
                break;
            case SearchInterface.AREA:
                result = repository.getList(SharedManager.AREAS);
                break;
            case SearchInterface.INGREDIENT:
                result = repository.getList(SharedManager.INGREDIENTS);
                break;
            case SearchInterface.CATEGORY:
                result = repository.getList(SharedManager.CATEGORIES);
                break;
        }
        return result;
    }
    public void saveFavorite(MealsItem item,DataFetch<Void> dataFetch){
        repository.insertFavoriteMealDataBase(item,dataFetch);
    }


    public String getRandomList(int type){
        int random = 0;
        String[] cashList;
        String reVal = "";
        switch (type){
            case SearchInterface.AREA:
                cashList = repository.getList(SharedManager.AREAS);
                random = new Random().nextInt(cashList.length);
                reVal = cashList[random];
                break;

            case SearchInterface.CATEGORY:
                cashList = repository.getList(SharedManager.CATEGORIES);
                random = new Random().nextInt(cashList.length);
                reVal = cashList[random];
                break;

            case SearchInterface.INGREDIENT:
                cashList = repository.getList(SharedManager.INGREDIENTS);
                random = new Random().nextInt(cashList.length);
                reVal = cashList[random];
                break;


        }
        return  reVal;
    }

    public void getSearchResultMeals(int type,String query){
        switch (type){
            case SearchInterface.AREA:
                repository.retrieveFilterResults(null, null, query,searchInterface);
                break;
            case SearchInterface.CATEGORY:
                repository.retrieveFilterResults(query, null, null,searchInterface);
                break;
            case SearchInterface.INGREDIENT:
                repository.retrieveFilterResults(null, query, null,searchInterface);
                break;
            case SearchInterface.SEARCH:
                repository.searchMealsByName(query,searchInterface);
                break;


        }


    }

}

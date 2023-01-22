package iti.android.foodplanner.ui.features.home;

import android.content.Context;

import java.util.List;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealsItem;

public class HomePresenter {
    public static final String AREA= "AREA";
    public static final String CATEGORY= "CATEGORY";
    public static final String INGREDIENT= "INGREDIENT";
    public static final String SINGLE= "SINGLE";
    private HomeInterface homeInterface;
    private Repository repository;

    public HomePresenter(Context context, HomeInterface homeInterface) {
        this.homeInterface = homeInterface;
        repository = Repository.getInstance(context);
    }


    public void getRandomMeals(String type,DataFetch<List<MealsItem>> dataFetch){
        switch (type){
            case AREA:
                repository.retrieveFilterResults(null, null, "Egyptian",dataFetch);
                break;
            case CATEGORY:
                repository.retrieveFilterResults("Beef", null, null,dataFetch);
                break;
            case INGREDIENT:
                repository.retrieveFilterResults(null, "Chicken", null,dataFetch);
                break;
            case SINGLE:
                repository.lookupSingleRandomMeal(dataFetch);
                break;


        }
    }

    public void saveFavorite(MealsItem item,DataFetch<Void> dataFetch){
        repository.insertFavoriteMealDataBase(item,dataFetch);
    }




    // TODO function get daily inspiration from [network or cash from ROOM]
    // TODO function get ArrayList of Categories Food



}

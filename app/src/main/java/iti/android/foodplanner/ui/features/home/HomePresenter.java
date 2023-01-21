package iti.android.foodplanner.ui.features.home;

import android.content.Context;

import java.util.List;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealsItem;

public class HomePresenter {
    private HomeInterface homeInterface;
    private Repository repository;

    public HomePresenter(Context context, HomeInterface homeInterface) {
        this.homeInterface = homeInterface;
        repository = Repository.getInstance(context);
    }

    public void getRandomMeals(DataFetch<List<MealsItem>> dataFetch){
        repository.retrieveFilterResults(null, null, "Egyptian",dataFetch);
    }

    public void saveFavorite(MealsItem item,DataFetch<Void> dataFetch){
        repository.insertFavoriteMealDataBase(item,dataFetch);
    }




    // TODO function get daily inspiration from [network or cash from ROOM]
    // TODO function get ArrayList of Categories Food



}

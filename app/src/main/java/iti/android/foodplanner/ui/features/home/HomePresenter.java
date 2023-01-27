package iti.android.foodplanner.ui.features.home;

import android.content.Context;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.data.shared.SharedManager;

public class HomePresenter {
    public static final String AREA= "AREA";
    public static final String CATEGORY= "CATEGORY";
    public static final String INGREDIENT= "INGREDIENT";
    public static final String SINGLE= "SINGLE";
    private HomeInterface homeInterface;
    private Repository repository;

    public boolean isUser = false;

    public HomePresenter(Context context, HomeInterface homeInterface) {
        this.homeInterface = homeInterface;
        repository = Repository.getInstance(context);
        isUser = repository.isUser();
    }



    public void getRandomMeals(String type,DataFetch<List<MealsItem>> dataFetch){
        int random = 0;
        String[] cashList;
        switch (type){
            case AREA:
                cashList = repository.getList(SharedManager.AREAS);
                random = new Random().nextInt(cashList.length);
                repository.retrieveFilterResults(null, null, cashList[random], dataFetch);
                break;
            case CATEGORY:
                cashList = repository.getList(SharedManager.CATEGORIES);
                random = new Random().nextInt(cashList.length);
                repository.retrieveFilterResults(cashList[random], null, null,dataFetch);
                break;
            case INGREDIENT:
                cashList = repository.getList(SharedManager.INGREDIENTS);
                random = new Random().nextInt(cashList.length);
                repository.retrieveFilterResults(null, cashList[random], null,dataFetch);
                break;
            case SINGLE:
                repository.lookupSingleRandomMeal(dataFetch);
                break;


        }
    }

    public void saveFavorite(MealsItem item,DataFetch<Void> dataFetch){
        repository.insertFavoriteMealDataBase(item,dataFetch);
    }

    public void deleteFavorite(MealsItem item,DataFetch<Void> dataFetch){
        repository.deleteFavorite(item,dataFetch);
    }



}

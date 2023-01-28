package iti.android.foodplanner.ui.features.details;

import android.content.Context;

import java.util.List;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealPlan;
import iti.android.foodplanner.data.models.meal.MealsItem;

public class DetailsPresenter {
    Context context;
    DetailsInterface detailsInterface;
    private Repository repository;
    //TODO check datafetch
    public DetailsPresenter(Context context,DetailsInterface detailsInterface) {
        this.detailsInterface = detailsInterface;
       repository=Repository.getInstance(context);
    }

    public void deleteFromPlan(MealPlan mealPlan){

        repository.deletePlanMeal(mealPlan,null);

    }

    public void deleteFromFav(MealsItem mealsItem){
        repository.deleteFavoriteRoom(mealsItem,null);
    }

    public void addToPlan(MealPlan mealPlan,DataFetch<Void> dataFetch){

        repository.insertPlaneMealRoom(mealPlan,dataFetch);

    }

    public void addToFav(MealsItem mealsItem, DataFetch<Void> dataFetch){
        repository.insertFavoriteMealDataBase(mealsItem,dataFetch);
    }
    public void getMeal(String mealId,DataFetch<List<MealsItem>> dataFetch )
    {
         repository.retrieveMealByID(mealId,dataFetch);
    }

    public boolean isUser(){
        return repository.isUser();
    }
    // TODO function Fetch data from ROOM or Network [ Details of food ]
    // TODO function Initialize Dialog of Add to Card
    // TODO function add to Favorite [ROOM]
}
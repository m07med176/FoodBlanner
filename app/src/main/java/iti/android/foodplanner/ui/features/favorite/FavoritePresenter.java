package iti.android.foodplanner.ui.features.favorite;

import android.content.Context;

import java.util.List;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealsItem;

public class FavoritePresenter {
    Context context;
    FavoriteInterface favoriteInterface;


    Repository repository;

    public boolean isUser = false;
    public FavoritePresenter(Context context, FavoriteInterface favoriteInterface) {
        repository  =Repository.getInstance(context);
        isUser = repository.isUser();
        this.favoriteInterface = favoriteInterface;
    }


    public void removeFavorite(MealsItem mealsItem,DataFetch<Void> dataFetch){
        repository.deleteFavoriteRoom(mealsItem,dataFetch);
    }

    public void getFavorites(DataFetch<List<MealsItem>> dataFetch) {
        repository.showFavouriteMealsRoom(dataFetch);

    }
}

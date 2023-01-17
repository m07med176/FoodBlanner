package iti.android.foodplanner.ui.features.favorite;

import android.content.Context;
import android.util.Log;

import java.util.Arrays;

import iti.android.foodplanner.data.Repository;

public class FavoritePresenter {
    Context context;
    FavoriteInterface favoriteInterface;
    Repository repository;
    public FavoritePresenter(Context context, FavoriteInterface favoriteInterface) {
        repository  =new Repository(context);
        this.favoriteInterface = favoriteInterface;
    }


    // TODO function remove items from table [ROOM]
    public String[] getData() {
        // TODO get data from favorite Table [ROOM]

        return new String[]{"Moamen","Moamen","Moamen","Moamen","Moamen","Moamen","Moamen"};
    }
}

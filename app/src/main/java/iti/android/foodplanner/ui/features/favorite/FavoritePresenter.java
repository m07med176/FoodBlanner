package iti.android.foodplanner.ui.features.favorite;

import android.content.Context;
import android.util.Log;

import java.util.Arrays;

public class FavoritePresenter {
    Context context;
    FavoriteInterface favoriteInterface;
    public FavoritePresenter(Context context, FavoriteInterface favoriteInterface) {
        this.favoriteInterface = favoriteInterface;
    }


    // TODO function remove items from table [ROOM]
    public String[] getData() {
        // TODO get data from favorite Table [ROOM]
        return new String[]{"Moamen","Moamen","Moamen","Moamen","Moamen","Moamen","Moamen"};
    }
}

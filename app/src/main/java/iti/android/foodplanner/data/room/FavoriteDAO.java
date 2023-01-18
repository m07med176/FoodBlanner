package iti.android.foodplanner.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import iti.android.foodplanner.data.models.meal.MealsItem;

@Dao
public interface FavoriteDAO {

    @Query("SELECT * FROM meals")
    public Observable<List<MealsItem>> showFavouriteMeals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertFavoriteMeal(MealsItem mealsItem);

    @Delete()
    public void deleteFavouriteMeal(MealsItem mealsItem);
}

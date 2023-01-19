package iti.android.foodplanner.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import iti.android.foodplanner.data.models.meal.MealsItem;

@Dao
public interface FavoriteDAO {

    @Query("SELECT * FROM meals")
    public Single<List<MealsItem>> showFavouriteMeals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertFavoriteMeal(MealsItem mealsItem);

    @Delete
    public Completable deleteFavouriteMeal(MealsItem mealsItem);
}

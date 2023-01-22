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
import iti.android.foodplanner.data.models.meal.MealPlan;
import iti.android.foodplanner.data.models.meal.MealsItem;

@Dao
public interface FavoriteDAO {

    @Query("SELECT * FROM meals")
    public Single<List<MealsItem>> showFavouriteMeals();

    @Query("DELETE FROM meals")
    public Completable removeAllTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertFavoriteMeal(MealsItem mealsItem);

    @Delete
    public Completable deleteFavouriteMeal(MealsItem mealsItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertAllTable(List<MealsItem> mealPlanList);
}

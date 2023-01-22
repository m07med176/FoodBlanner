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


@Dao
public interface PlaneFoodDAO {
   @Query("SELECT * FROM plans")
    public Single<List<MealPlan>> showPlanMeals();

  @Query("DELETE FROM meals")
  public Completable removeAllTable();

 @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertPlanMeal(MealPlan mealPlan);

    @Delete
    public Completable deletePlanMeal(MealPlan mealPlan);
}

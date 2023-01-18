package iti.android.foodplanner.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import iti.android.foodplanner.data.models.meal.MealPlan;


@Dao
public interface PlaneFoodDAO {
   @Query("SELECT * FROM plans")
    public Observable<List<MealPlan>> showPlanMeals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPlanMeal(MealPlan mealPlan);

    @Delete()
    public void deletePlanMeal(MealPlan mealPlan);
}

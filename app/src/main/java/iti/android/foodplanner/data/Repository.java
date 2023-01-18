package iti.android.foodplanner.data;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import iti.android.foodplanner.data.authentication.Authentication;
import iti.android.foodplanner.data.authentication.AuthenticationFactory;
import iti.android.foodplanner.data.models.area.AreasList;
import iti.android.foodplanner.data.models.categoryFeed.CategoriesItem;
import iti.android.foodplanner.data.models.category.CategoriesList;
import iti.android.foodplanner.data.models.Ingredient.IngredientsList;
import iti.android.foodplanner.data.models.meal.MealPlan;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.data.models.meal.MealsList;
import iti.android.foodplanner.data.network.ApiCalls;
import iti.android.foodplanner.data.network.Network;
import iti.android.foodplanner.data.room.RoomDatabase;
import iti.android.foodplanner.data.shared.SharedManager;

/**
 * Gather all functions from Network, Room Database And Shared Manager
 */
public class Repository {
    public ApiCalls apiCalls;
    public RoomDatabase roomDatabase;
    public SharedManager sharedManager;

    public Repository(Context context) {
            apiCalls = Network.apiCalls;
            roomDatabase = RoomDatabase.getInstance(context);
            sharedManager = SharedManager.getInstance(context);
    }
    //Favorite Meals
    public void insertFavoriteMealDataBase(MealsItem mealsItem){
        roomDatabase.FavoriteDAO().insertFavoriteMeal(mealsItem);
    }

    public Observable<List<MealsItem>> showFavouriteMealsDataBase(){
        return roomDatabase.FavoriteDAO().showFavouriteMeals();
    }
    public void deleteFavorite(MealsItem mealsItem){
        roomDatabase.FavoriteDAO().deleteFavouriteMeal(mealsItem);
    }

    //Plan meal
    public void insertPlanMeal(MealPlan mealPlan){
        roomDatabase.PlaneFoodDAO().insertPlanMeal(mealPlan);
    }

    public Observable<List<MealPlan>> showMealPlan(){
        return roomDatabase.PlaneFoodDAO().showPlanMeals();
    }
    public void deletePlanMeal(MealPlan mealPlan){
        roomDatabase.PlaneFoodDAO().deletePlanMeal(mealPlan);
    }


    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/random.php">Single Random Meal</a>
     * @return List<MealsItem>
     */
    public Observable<MealsList> lookupSingleRandomMeal(){
            return apiCalls.lookupSingleRandomMeal().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    };


    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?i=list">List of Ingredients</a>
     * @return List<Ingredient>
     */
    public Observable<IngredientsList> ingredientsList(){
        return apiCalls.ingredientsList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    };


    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?c=list">List of Categories</a>
     * @return List<Category>
     */
    public Observable<CategoriesList> categoriesList(){
        return apiCalls.categoriesList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    };

    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?a=list">List of Areas</a>
     * @return List<Area>
     */
    public Observable<AreasList> areasList(){
        return apiCalls.areasList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    };


    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/filter.php?c=Dessert"> Result of Short Meals</a>
     * @return List<ShortMeals>
     */

    public Observable<MealsList> retrieveFilterResults(
            String category,
            String ingredient,
            String area
    ){
        return apiCalls.retrieveFilterResults(category,ingredient,area).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    };

    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/categories.php">Categories List</a>
     * @return List<CategoriesItem>
     */
    public Observable<List<CategoriesItem>> retrieveCategoriesList(){
        return apiCalls.retrieveCategoriesList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    };


    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/search.php?s='Q'">Search By Name</a>
     * @return List<MealsItem>
     */
    public Observable<MealsList> searchMealsByName(String search){
        return apiCalls.searchMealsByName(search).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    };

    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/lookup.php?i='525'">Retrieve Meals By ID</a>
     * @return List<MealsItem>
     */

    public Observable<MealsList> retrieveMealByID(String id ){
        return apiCalls.retrieveMealByID(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    };



}

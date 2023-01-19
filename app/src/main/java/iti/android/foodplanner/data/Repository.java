package iti.android.foodplanner.data;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import iti.android.foodplanner.data.authentication.Authentication;
import iti.android.foodplanner.data.authentication.AuthenticationFactory;
import iti.android.foodplanner.data.models.area.Area;
import iti.android.foodplanner.data.models.area.AreasList;
import iti.android.foodplanner.data.models.categoryFeed.CategoriesFeed;
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
    private ApiCalls apiCalls;
    private RoomDatabase roomDatabase;
    private SharedManager sharedManager;

    public Repository(Context context) {
            apiCalls = Network.apiCalls;
            roomDatabase = RoomDatabase.getInstance(context);
            sharedManager = SharedManager.getInstance(context);
    }

    // region ROOM
    public void insertFavoriteMealDataBase(MealsItem mealsItem,DataFetch<Void> dataFetch){
        roomDatabase
                .FavoriteDAO()
                .insertFavoriteMeal(mealsItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        dataFetch.onDataLoading();
                    }

                    @Override
                    public void onComplete() {
                        dataFetch.onDataSuccessResponse(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });
    }

    public void showFavouriteMealsDataBase(DataFetch<List<MealsItem>> dataFetch){
        roomDatabase
                .FavoriteDAO()
                .showFavouriteMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<MealsItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        dataFetch.onDataLoading();
                    }

                    @Override
                    public void onSuccess(@NonNull List<MealsItem> mealsItems) {
                        dataFetch.onDataSuccessResponse(mealsItems);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });
    }
    public void deleteFavorite(MealsItem mealsItem,DataFetch<Void> dataFetch){
        roomDatabase.FavoriteDAO()
                .deleteFavouriteMeal(mealsItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        dataFetch.onDataLoading();
                    }

                    @Override
                    public void onComplete() {
                        dataFetch.onDataSuccessResponse(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });
    }

    public void insertPlanMeal(MealPlan mealPlan,DataFetch<Void> dataFetch){
        roomDatabase.PlaneFoodDAO().insertPlanMeal(mealPlan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        dataFetch.onDataLoading();
                    }

                    @Override
                    public void onComplete() {
                        dataFetch.onDataSuccessResponse(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });
    }

    public void showMealPlan(DataFetch<List<MealPlan>> dataFetch){
        roomDatabase
                .PlaneFoodDAO()
                .showPlanMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<MealPlan>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        dataFetch.onDataLoading();
                    }

                    @Override
                    public void onSuccess(@NonNull List<MealPlan> mealPlans) {
                        dataFetch.onDataSuccessResponse(mealPlans);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });
    }
    public void deletePlanMeal(MealPlan mealPlan,DataFetch<Void> dataFetch){
        roomDatabase.PlaneFoodDAO()
                .deletePlanMeal(mealPlan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        dataFetch.onDataLoading();
                    }

                    @Override
                    public void onComplete() {
                            dataFetch.onDataSuccessResponse(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });
    }
    // endregion ROOM

    // region APIs

    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/random.php">Single Random Meal</a>
     * @return List<MealsItem>
     */
    public void lookupSingleRandomMeal(DataFetch<MealsList> dataFetch){
        apiCalls.lookupSingleRandomMeal().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<MealsList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                dataFetch.onDataLoading();
            }

            @Override
            public void onSuccess(@NonNull MealsList mealsList) {
                dataFetch.onDataSuccessResponse(mealsList);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                dataFetch.onDataFailedResponse(e.getMessage());
            }
        });
    };


    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?i=list">List of Ingredients</a>
     * @return List<Ingredient>
     */
    public void ingredientsList(DataFetch<IngredientsList> dataFetch){
        apiCalls.ingredientsList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<IngredientsList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                dataFetch.onDataLoading();
            }

            @Override
            public void onSuccess(@NonNull IngredientsList ingredientsList) {
                dataFetch.onDataSuccessResponse(ingredientsList);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                dataFetch.onDataFailedResponse(e.getMessage());
            }
        });
    };


    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?c=list">List of Categories</a>
     * @return List<Category>
     */
    public void categoriesList(DataFetch<CategoriesList> dataFetch){
        apiCalls.categoriesList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CategoriesList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        dataFetch.onDataLoading();
                    }

                    @Override
                    public void onSuccess(@NonNull CategoriesList categoriesList) {
                        dataFetch.onDataSuccessResponse(categoriesList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });

    };

    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?a=list">List of Areas</a>
     * @return List<Area>
     */
    public void areasList(DataFetch<List<Area>> dataFetch){
        apiCalls.areasList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AreasList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        dataFetch.onDataLoading();
                    }

                    @Override
                    public void onSuccess(@NonNull AreasList areasList) {
                        dataFetch.onDataSuccessResponse(areasList.getAreas());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });

    };


    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/filter.php?c=Dessert"> Result of Short Meals</a>
     * @return List<ShortMeals>
     */

    public void retrieveFilterResults(
            String category,
            String ingredient,
            String area,
            DataFetch<List<MealsItem>> dataFetch
    ){
        apiCalls
                .retrieveFilterResults(category,ingredient,area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MealsList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        dataFetch.onDataLoading();
                    }

                    @Override
                    public void onSuccess(@NonNull MealsList mealsList) {
                        dataFetch.onDataSuccessResponse(mealsList.getMeals());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });;

    };

    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/categories.php">Categories List</a>
     * @return List<CategoriesItem>
     */
    public void retrieveCategoriesList(DataFetch<List<CategoriesItem>> dataFetch){

         apiCalls
                 .retrieveCategoriesList()
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new SingleObserver<CategoriesFeed>() {
                     @Override
                     public void onSubscribe(@NonNull Disposable d) {
                         dataFetch.onDataLoading();
                     }

                     @Override
                     public void onSuccess(@NonNull CategoriesFeed categoriesFeed) {
                         dataFetch.onDataSuccessResponse(categoriesFeed.getCategories());
                     }

                     @Override
                     public void onError(@NonNull Throwable e) {
                            dataFetch.onDataFailedResponse(e.getMessage());
                     }
                 })
         ;
    };


    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/search.php?s='Q'">Search By Name</a>
     * @return List<MealsItem>
     */
    public void searchMealsByName(String search,DataFetch<List<MealsItem>> dataFetch){
        apiCalls
                .searchMealsByName(search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MealsList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        dataFetch.onDataLoading();
                    }

                    @Override
                    public void onSuccess(@NonNull MealsList mealsList) {
                        dataFetch.onDataSuccessResponse(mealsList.getMeals());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });;
    };

    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/lookup.php?i='525'">Retrieve Meals By ID</a>
     * @return List<MealsItem>
     */

    public void retrieveMealByID(String id,DataFetch<List<MealsItem>> dataFetch ){

         apiCalls.retrieveMealByID(id)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new SingleObserver<MealsList>() {
                     @Override
                     public void onSubscribe(@NonNull Disposable d) {
                         dataFetch.onDataLoading();
                     }

                     @Override
                     public void onSuccess(@NonNull MealsList mealsList) {
                         dataFetch.onDataSuccessResponse(mealsList.getMeals());
                     }

                     @Override
                     public void onError(@NonNull Throwable e) {
                         dataFetch.onDataFailedResponse(e.getMessage());
                     }
                 });
    };


    // endregion APIs
}
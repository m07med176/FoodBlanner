package iti.android.foodplanner.data;

import android.content.Context;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import iti.android.foodplanner.data.backup.BackupManager;
import iti.android.foodplanner.data.models.User;
import iti.android.foodplanner.data.models.selections.Ingredient.Ingredient;
import iti.android.foodplanner.data.models.selections.area.Area;
import iti.android.foodplanner.data.models.selections.area.AreasList;
import iti.android.foodplanner.data.models.selections.category.Category;
import iti.android.foodplanner.data.models.selections.categoryFeed.CategoriesFeed;
import iti.android.foodplanner.data.models.selections.categoryFeed.CategoriesItem;
import iti.android.foodplanner.data.models.selections.category.CategoriesList;
import iti.android.foodplanner.data.models.selections.Ingredient.IngredientsList;
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
    private final ApiCalls apiCalls;
    private final RoomDatabase roomDatabase;
    private final BackupManager backupManager;
    private final SharedManager sharedManager;
    public static Repository repository = null;
    public static Repository getInstance(Context context){
        if (repository==null)
            repository = new Repository(context);
        return repository;
    }

    private Repository(Context context) {
            apiCalls = Network.apiCalls;
            roomDatabase = RoomDatabase.getInstance(context);
            sharedManager = SharedManager.getInstance(context);
            backupManager = BackupManager.getInstance(sharedManager);
    }

    // region Shared
    public boolean isUser(){
        return sharedManager.isUser();
    }

    public void saveUser(User user){
        sharedManager.saveUser(user);
    }

    public User getUser(){
        return sharedManager.getUser();
    }
    public boolean isFirstEntrance(){
        return sharedManager.isFirstEntrance();
    }


    public void saveEntrance(){
        sharedManager.saveEntrance();
    }


    // endregion Shared
    // region ROOM
    private void insertFavoriteToRoom(MealsItem mealsItem,DataFetch<Void> dataFetch){
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
                        backupManager.saveFavorite(mealsItem);
                        dataFetch.onDataSuccessResponse(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });
    }
    public void insertFavoriteMealDataBase(MealsItem mealsItem,DataFetch<Void> dataFetch){
        apiCalls.retrieveMealByID(mealsItem.getIdMeal())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MealsList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MealsList mealsList) {
                        insertFavoriteToRoom(mealsList.getMeals().get(0),dataFetch);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

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
                        backupManager.deleteFavorite(mealsItem);
                        dataFetch.onDataSuccessResponse(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });
    }

    public void insertPlaneMealDataBase(MealPlan mealPlan,DataFetch<Void> dataFetch){
        apiCalls.retrieveMealByID(mealPlan.getIdMeal())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MealsList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MealsList mealsList) {
                        insertPlanMealRoom(mealPlan.migrateMealsToPlaneModel(mealsList.getMeals().get(0)),dataFetch);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    private void insertPlanMealRoom(MealPlan mealPlan, DataFetch<Void> dataFetch){
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
                            backupManager.deletePlane(mealPlan);
                            dataFetch.onDataSuccessResponse(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            dataFetch.onDataFailedResponse(e.getMessage());
                    }
                });
    }
    // endregion ROOM
    // region API

    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/random.php">Single Random Meal</a>
     * @return List<MealsItem>
     */
    public void lookupSingleRandomMeal(DataFetch<List<MealsItem>> dataFetch){
        apiCalls.lookupSingleRandomMeal().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<MealsList>() {
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


    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?i=list">List of Ingredients</a>
     * @return List<Ingredient>
     */
    public void ingredientsList(DataFetch<List<Ingredient>> dataFetch){
        apiCalls.ingredientsList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<IngredientsList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                dataFetch.onDataLoading();
            }

            @Override
            public void onSuccess(@NonNull IngredientsList ingredientsList) {
                dataFetch.onDataSuccessResponse(ingredientsList.getMeals());
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
    public void categoriesList(DataFetch<List<Category>> dataFetch){
        apiCalls.categoriesList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CategoriesList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        dataFetch.onDataLoading();
                    }

                    @Override
                    public void onSuccess(@NonNull CategoriesList categoriesList) {
                        dataFetch.onDataSuccessResponse(categoriesList.getCategory());
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

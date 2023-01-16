package iti.android.foodplanner.data.network;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import iti.android.foodplanner.data.models.Area;
import iti.android.foodplanner.data.models.CategoriesItem;
import iti.android.foodplanner.data.models.Category;
import iti.android.foodplanner.data.models.Ingredient;
import iti.android.foodplanner.data.models.MealsItem;
import iti.android.foodplanner.data.models.ShortMeals;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCalls {
    String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/random.php">Single Random Meal</a>
     * @return List<MealsItem>
     */
    @GET("random.php")
    public Observable<List<MealsItem>> lookupSingleRandomMeal();


    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?i=list">List of Ingredients</a>
     * @return List<Ingredient>
     */
    @GET("list.php?i=list")
    public Observable<List<Ingredient>> ingredientsList();


    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?c=list">List of Categories</a>
     * @return List<Category>
     */
    @GET("list.php?c=list")
    public Observable<List<Category>> categoriesList();

    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?a=list">List of Areas</a>
     * @return List<Area>
     */
    @GET("list.php?a=list")
    public Observable<List<Area>> areasList();


    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/filter.php?c=Dessert"> Result of Short Meals</a>
     * @return List<ShortMeals>
     */

    @GET("filter.php")
    public Observable<List<ShortMeals>> retrieveFilterResults(
            @Query("c") String category,
            @Query("i") String ingredient,
            @Query("a") String area
    );

    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/categories.php">Categories List</a>
     * @return List<CategoriesItem>
     */
    @GET("categories.php")
    public Observable<List<CategoriesItem>> retrieveCategoriesList();


    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/search.php?s='Q'">Search By Name</a>
     * @return List<MealsItem>
     */
    @GET("search.php")
    public Observable<List<MealsItem>> searchMealsByName(
            @Query("s") String search
    );

    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/lookup.php?i='525'">Retrieve Meals By ID</a>
     * @return List<MealsItem>
     */
    @GET("lookup.php")
    public Observable<List<MealsItem>> retrieveMealByID(
            @Query("i") String id
    );



}

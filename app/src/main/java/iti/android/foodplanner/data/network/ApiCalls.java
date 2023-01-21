package iti.android.foodplanner.data.network;

import io.reactivex.rxjava3.core.Single;
import iti.android.foodplanner.data.models.selections.area.AreasList;
import iti.android.foodplanner.data.models.selections.categoryFeed.CategoriesFeed;
import iti.android.foodplanner.data.models.selections.category.CategoriesList;
import iti.android.foodplanner.data.models.selections.Ingredient.IngredientsList;
import iti.android.foodplanner.data.models.meal.MealsList;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCalls {
    String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/random.php">Single Random Meal</a>
     * @return List<MealsItem>
     */
    @GET("random.php")
    public Single<MealsList> lookupSingleRandomMeal();


    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?i=list">List of Ingredients</a>
     * @return List<Ingredient>
     */
    @GET("list.php?i=list")
    public Single<IngredientsList> ingredientsList();


    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?c=list">List of Categories</a>
     * @return List<Category>
     */
    @GET("list.php?c=list")
    public Single<CategoriesList> categoriesList();

    /**
     * link: <a href="https://www.themealdb.com/api/json/v1/1/list.php?a=list">List of Areas</a>
     * @return List<Area>
     */
    @GET("list.php?a=list")
    public Single<AreasList> areasList();


    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/filter.php?c=Dessert"> Result of Short Meals</a>
     * @return List<ShortMeals>
     */

    @GET("filter.php")
    public Single<MealsList> retrieveFilterResults(
            @Query("c") String category,
            @Query("i") String ingredient,
            @Query("a") String area
    );

    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/categories.php">Categories List</a>
     * @return List<CategoriesItem>
     */
    @GET("categories.php")
    public Single<CategoriesFeed> retrieveCategoriesList();


    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/search.php?s='Q'">Search By Name</a>
     * @return List<MealsItem>
     */
    @GET("search.php")
    public Single<MealsList> searchMealsByName(@Query("s") String search);

    /**
     * Link <a href="https://www.themealdb.com/api/json/v1/1/lookup.php?i='525'">Retrieve Meals By ID</a>
     * @return List<MealsItem>
     */
    @GET("lookup.php")
    public Single<MealsList> retrieveMealByID(@Query("i") String id);



}

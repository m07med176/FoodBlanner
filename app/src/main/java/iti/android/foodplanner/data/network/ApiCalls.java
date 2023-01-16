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
     * link: https://www.themealdb.com/api/json/v1/1/random.php
     * @return Object
     */
    @GET("random.php")
    public Observable<List<MealsItem>> lookupSingleRandomMeal();


    /**
     * link: https://www.themealdb.com/api/json/v1/1/list.php?i=list
     * @return Ingredients
     */
    @GET("list.php?i=list")
    public Observable<List<Ingredient>> ingredientsList();


    /**
     * link: https://www.themealdb.com/api/json/v1/1/list.php?c=list
     * @return Categories
     */
    @GET("list.php?c=list")
    public Observable<List<Category>> categoriesList();

    /**
     * link: https://www.themealdb.com/api/json/v1/1/list.php?a=list
     * @return Areas
     */
    @GET("list.php?a=list")
    public Observable<List<Area>> areasList();


    /**
     * link: https://www.themealdb.com/api/json/v1/1/filter.php?c=Dessert
     * @return FilterResults
     */

    @GET("filter.php")
    public Observable<List<ShortMeals>> retrieveFilterResults(
            @Query("c") String category,
            @Query("i") String ingredient,
            @Query("a") String area
    );

    @GET("categories.php")
    public Observable<List<CategoriesItem>> retrieveCategoriesList();


    @GET("search.php")
    public Observable<List<MealsItem>> searchMeals(
            @Query("s") String search
    );

    @GET("lookup.php")
    public Observable<List<MealsItem>> retrieveMealByID(
            @Query("i") String id
    );
    


}

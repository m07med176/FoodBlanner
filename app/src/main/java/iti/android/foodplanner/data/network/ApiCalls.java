package iti.android.foodplanner.data.network;

import io.reactivex.rxjava3.core.Observable;
import iti.android.foodplanner.data.models.Areas;
import iti.android.foodplanner.data.models.Categories;
import iti.android.foodplanner.data.models.FilterResults;
import iti.android.foodplanner.data.models.Ingredients;
import iti.android.foodplanner.data.models.Meals;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCalls {
    String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    /**
     * link: https://www.themealdb.com/api/json/v1/1/random.php
     * @return Object
     */
    @GET("random.php")
    public Observable<Meals> lookupSingleRandomMeal();


    /**
     * link: https://www.themealdb.com/api/json/v1/1/list.php?i=list
     * @return Ingredients
     */
    @GET("list.php?i=list")
    public Observable<Ingredients> ingredientsList();


    /**
     * link: https://www.themealdb.com/api/json/v1/1/list.php?c=list
     * @return Categories
     */
    @GET("list.php?c=list")
    public Observable<Categories> categoriesList();

    /**
     * link: https://www.themealdb.com/api/json/v1/1/list.php?a=list
     * @return Areas
     */
    @GET("list.php?a=list")
    public Observable<Areas> areasList();


    /**
     * link: https://www.themealdb.com/api/json/v1/1/filter.php?c=Dessert
     * @return FilterResults
     */

    @GET("filter.php")
    public Observable<FilterResults> retrieveFilterResults(
            @Query("c") String category,
            @Query("i") String ingredient,
            @Query("a") String area
    );


    // TODO Moamen [DATA] : search food by name
    // TODO Moamen [DATA] : filter by id
    // TODO Moamen [DATA] : show all categories

}

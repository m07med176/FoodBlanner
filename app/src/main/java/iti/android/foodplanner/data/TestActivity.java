package iti.android.foodplanner.data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.area.AreasList;
import iti.android.foodplanner.data.models.meal.MealPlan;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.data.room.Week;

public class TestActivity extends AppCompatActivity {

    private  Repository repository;
    MealsItem mealsItem;
    MealPlan mealPlan;
    private static final String TAG = "TestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.d(TAG, "onCreate: TEST");
      mealsItem = new MealsItem();
        mealsItem.setIdMeal("565254");
        mealsItem.setStrArea("hskjld");
        mealsItem.setStrCategory("hskjld");
        mealsItem.setStrMealThumb("hskjld");
        //new Thread(()->{deleteFavouriteMealTEST(mealsItem);});

        mealPlan=new MealPlan();
        mealPlan.setDay(Week.SAT);
        mealPlan.setIdMeal("254");
        repository = new Repository(this);
        //new Thread(()->{deletePlanMealTEST(mealPlan);});

//       Observable observable =Observable.just(mealsItem);
//     observable.observeOn(Schedulers.io()).subscribe(insetFavortieTest());
////
//       Observable observableplan =Observable.just(mealPlan);
//        observableplan.observeOn(Schedulers.io()).subscribe(insertPlanMeal());


//        showItemsTest().subscribe(item->{
//            Log.i(TAG, "database table fav: " + item.get(0).getIdMeal());
//        });
//
//        showPlanItemsTest().subscribe(item->{
//           Log.i(TAG, "database table plan: " + item.get(0).getDay());
//      });


        //deletePlanMealTEST(mealPlan);
//        repository.areasList().subscribe(this::handleResults,this::handleError);

//        repository.retrieveMealByID("52772").subscribe(data->{ Log.d(TAG, "onCreate: "+data.getMeals().size());},this::handleError);

//        repository.searchMealsByName("Arrabiata").subscribe(data->{
//            Toast.makeText(this, data.getMeals().get(0).getStrArea(), Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "onCreate: "+data.getMeals().size());
//            },this::handleError);

//        repository.lookupSingleRandomMeal().subscribe(data->{
//            Toast.makeText(this, data.getMeals().get(0).getStrArea(), Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "onCreate: "+data.getMeals().size());
//        },this::handleError);

//          repository.ingredientsList().subscribe(data->{
//            Toast.makeText(this, data.getMeals().get(0).getIdIngredient(), Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "onCreate: "+data.getMeals().size());
//        },this::handleError);


//        repository.categoriesList().
//                subscribe(data->{
//        Toast.makeText(this, data.getCategory().get(0).getStrCategory(), Toast.LENGTH_SHORT).show();
//        },this::handleError);

//        repository.retrieveFilterResults(null,null,"Egyptian").subscribe(data->{
//        Toast.makeText(this, data.getMeals().get(0).getStrCategory(), Toast.LENGTH_SHORT).show();
//        },this::handleError);





    }



    private Observer insertPlanMeal(){
        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                repository.insertPlanMeal((MealPlan) o);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        return observer;
    }

    private Observer<MealsItem> insetFavortieTest() {
        Observer observer=new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                repository.insertFavoriteMealDataBase((MealsItem) o);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.getMessage();
            }

            @Override
            public void onComplete() {

            }
        };
       return observer;

    }
    public Observable<List<MealPlan>> showPlanItemsTest(){
        return repository.showMealPlan();
    }
    public Observable<List<MealsItem>> showItemsTest(){
        return repository.showFavouriteMealsDataBase();
    }
    public void deleteFavouriteMealTEST(MealsItem mealsItem){
        repository.deleteFavorite(mealsItem);
    }
    public void deletePlanMealTEST(MealPlan mealPlan){
        repository.deletePlanMeal(mealPlan);
    }
    private void handleResults(AreasList marketList) {
        Log.d(TAG, "onCreate: "+marketList.getAreas().size());
        Toast.makeText(this, "onCreate: "+marketList.getAreas().size(), Toast.LENGTH_SHORT).show();
    }

    private void handleError(Throwable t) {

        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }
}
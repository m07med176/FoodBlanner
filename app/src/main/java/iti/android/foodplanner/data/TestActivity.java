package iti.android.foodplanner.data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.User;
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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        User user = new User("das","asda","asdas","asd");
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });

        Map<String, Object> user = new HashMap<>();
        user.put("first", "Alan");
        user.put("middle", "Mathison");
        user.put("last", "Turing");
        user.put("born", 1912);

//                        .document()
        db
                .collection("users")
                .add("user")
                .addOnCompleteListener(task -> Toast.makeText(TestActivity.this, "Gooooolge", Toast.LENGTH_SHORT).show())
                .addOnCanceledListener(() -> Log.e(TAG, "onCanceled: " ));

        // Add a new document with a generated ID
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.e(TAG, "onComplete: "+task.isSuccessful() );
                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Log.e(TAG, "onCanceled: " );
                    }
                });
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(TestActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
       /*
        Log.d(TAG, "onCreate: TEST");

        mealsItem = new MealsItem();
        mealsItem.setIdMeal("565254");
        mealsItem.setStrArea("hskjld");
        mealsItem.setStrCategory("hskjld");
        mealsItem.setStrMealThumb("hskjld");
        */

        //new Thread(()->{deleteFavouriteMealTEST(mealsItem);});

        /*
        mealPlan=new MealPlan();
        mealPlan.setDay(Week.SAT);
        mealPlan.setIdMeal("254");
        repository = new Repository(this);
*/
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


/*
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
        repository.deletePlanMeal(mealPlan, new DataFetch<Void>() {
            @Override
            public void onDataSuccessResponse(Void data) {

            }

            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });
    }
    private void handleResults(AreasList marketList) {
        Log.d(TAG, "onCreate: "+marketList.getAreas().size());
        Toast.makeText(this, "onCreate: "+marketList.getAreas().size(), Toast.LENGTH_SHORT).show();
    }

    private void handleError(Throwable t) {

        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }

 */
}
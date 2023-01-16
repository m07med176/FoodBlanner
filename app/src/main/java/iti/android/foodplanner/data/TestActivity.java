package iti.android.foodplanner.data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.area.AreasList;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.d(TAG, "onCreate: TEST");
        Repository repository = new Repository(this);
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


        repository.categoriesList().
                subscribe(data->{
        Toast.makeText(this, data.getCategory().get(0).getStrCategory(), Toast.LENGTH_SHORT).show();
        },this::handleError);

//        repository.retrieveFilterResults(null,null,"Egyptian").subscribe(data->{
//        Toast.makeText(this, data.getMeals().get(0).getStrCategory(), Toast.LENGTH_SHORT).show();
//        },this::handleError);




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
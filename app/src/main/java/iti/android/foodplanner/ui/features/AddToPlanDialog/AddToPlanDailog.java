package iti.android.foodplanner.ui.features.AddToPlanDialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.checkerframework.checker.units.qual.C;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealPlan;
import iti.android.foodplanner.data.room.Week;

public class AddToPlanDailog {
    Repository repository;
    Context context;
    public AddToPlanDailog(Context context) {

        this.context=context;
        repository = Repository.getInstance(context);
    }

    public void createDialog(MealPlan mealPlan, Context context){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setIcon(R.drawable.ic_plane);
        dialogBuilder.setTitle("Choose any day you want");

        final ArrayAdapter<String> days = new ArrayAdapter<String>(context, android.R.layout.select_dialog_singlechoice);
        days.add(Week.SATURDAY.toString());
        days.add(Week.SUNDAY.toString());
        days.add(Week.MONDAY.toString());
        days.add(Week.THURSDAY.toString());
        days.add(Week.WEDNESDAY.toString());
        days.add(Week.TUESDAY.toString());
        days.add(Week.FRIDAY.toString());

        dialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogBuilder.setAdapter(days, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("TAG", "meal plan at dialog: " + mealPlan.getStrCategory());
                if(which==0)
                {
                    mealPlan.setDay(Week.SATURDAY);
                }
                if(which==1)
                {
                    mealPlan.setDay(Week.SUNDAY);
                }
                if(which==2)
                {
                    mealPlan.setDay(Week.MONDAY);
                }
                if(which==3)
                {
                    mealPlan.setDay(Week.THURSDAY);
                }
                if(which==4)
                {
                    mealPlan.setDay(Week.WEDNESDAY);
                }
                if(which==5)
                {
                    mealPlan.setDay(Week.TUESDAY);
                }
                if(which==6)
                {
                    mealPlan.setDay(Week.FRIDAY);
                }
                addToPlan(mealPlan);
                Toast.makeText(context,"Added to plan successfully",Toast.LENGTH_SHORT).show();

            }
        });
        dialogBuilder.show();
    }
    private void addToPlan(MealPlan mealPlan) {
        repository.insertPlaneMealRoom(mealPlan, new DataFetch<Void>() {
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
}

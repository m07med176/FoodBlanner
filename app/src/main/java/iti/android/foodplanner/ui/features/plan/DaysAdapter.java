package iti.android.foodplanner.ui.features.plan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealPlan;
import iti.android.foodplanner.data.room.Week;


public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder>{
    static Context context;
    private List<Week> values;
    private MealsAdapter mealsAdapter;

    Repository repository;

    public DaysAdapter(Context context,List<Week> dataset) {
        this.context = context;
        values=dataset;
        repository=Repository.getInstance(context);
    }

    @NonNull
    @Override
    public DaysAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.plan_days_row,parent,false);


        return new DaysAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysAdapter.ViewHolder holder, int position) {
        holder.dayTxtView.setText(values.get(position).toString());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        holder.mealsRecyclerView.setLayoutManager(linearLayoutManager);
        switch (values.get(position))
        {
            case SATURDAY:
                repository.showPlanMealsByDay(Week.SATURDAY, new DataFetch<List<MealPlan>>() {
                    @Override
                    public void onDataSuccessResponse(List<MealPlan> data) {
                        if(data.isEmpty())
                        {
                            holder.emptyView.setVisibility(View.VISIBLE);
                        }
                        mealsAdapter=new MealsAdapter(context,data);
                        holder.mealsRecyclerView.setAdapter(mealsAdapter);
                        Log.i("RoomTAG", "onDataSuccessResponse:from room "+data.size());
                    }

                    @Override
                    public void onDataFailedResponse(String message) {

                    }

                    @Override
                    public void onDataLoading() {

                    }
                });
                break;
            case SUNDAY:
                repository.showPlanMealsByDay(Week.SUNDAY, new DataFetch<List<MealPlan>>() {
                    @Override
                    public void onDataSuccessResponse(List<MealPlan> data) {
                        if(data.isEmpty())
                        {
                            holder.emptyView.setVisibility(View.VISIBLE);
                        }
                        mealsAdapter=new MealsAdapter(context,data);
                        holder.mealsRecyclerView.setAdapter(mealsAdapter);
                        Log.i("RoomTAG", "onDataSuccessResponse:from room "+data.size());
                    }

                    @Override
                    public void onDataFailedResponse(String message) {

                    }

                    @Override
                    public void onDataLoading() {

                    }
                });
                break;
            case MONDAY:
                repository.showPlanMealsByDay(Week.MONDAY, new DataFetch<List<MealPlan>>() {
                    @Override
                    public void onDataSuccessResponse(List<MealPlan> data) {
                        if(!data.isEmpty())
                        {
                            holder.emptyView.setText(" ");
                            mealsAdapter = new MealsAdapter(context, data);
                            holder.mealsRecyclerView.setAdapter(mealsAdapter);
                        }
                        holder.emptyView.setText("View VISIBLE");

                        Log.i("RoomTAG", "onDataSuccessResponse:from room "+data.size());
                    }

                    @Override
                    public void onDataFailedResponse(String message) {

                    }

                    @Override
                    public void onDataLoading() {

                    }
                });
                break;
            case TUESDAY:
                repository.showPlanMealsByDay(Week.TUESDAY, new DataFetch<List<MealPlan>>() {
                    @Override
                    public void onDataSuccessResponse(List<MealPlan> data) {
                        if(data.isEmpty())
                        {
                            holder.emptyView.setVisibility(View.VISIBLE);
                        }
                        mealsAdapter=new MealsAdapter(context,data);
                        holder.mealsRecyclerView.setAdapter(mealsAdapter);
                        Log.i("RoomTAG", "onDataSuccessResponse:from room "+data.size());
                    }

                    @Override
                    public void onDataFailedResponse(String message) {

                    }

                    @Override
                    public void onDataLoading() {

                    }
                });
                break;
            case WEDNESDAY:
                repository.showPlanMealsByDay(Week.WEDNESDAY, new DataFetch<List<MealPlan>>() {
                    @Override
                    public void onDataSuccessResponse(List<MealPlan> data) {
                        if(data.isEmpty())
                        {
                            holder.emptyView.setVisibility(View.VISIBLE);
                        }
                        mealsAdapter=new MealsAdapter(context,data);
                        holder.mealsRecyclerView.setAdapter(mealsAdapter);
                        Log.i("RoomTAG", "onDataSuccessResponse:from room "+data.size());
                    }

                    @Override
                    public void onDataFailedResponse(String message) {

                    }

                    @Override
                    public void onDataLoading() {

                    }
                });
                break;
            case THURSDAY:
                repository.showPlanMealsByDay(Week.THURSDAY, new DataFetch<List<MealPlan>>() {
                    @Override
                    public void onDataSuccessResponse(List<MealPlan> data) {
                        if(data.isEmpty())
                        {
                            holder.emptyView.setVisibility(View.VISIBLE);
                        }
                        mealsAdapter=new MealsAdapter(context,data);
                        holder.mealsRecyclerView.setAdapter(mealsAdapter);
                        Log.i("RoomTAG", "onDataSuccessResponse:from room "+data.size());
                    }

                    @Override
                    public void onDataFailedResponse(String message) {

                    }

                    @Override
                    public void onDataLoading() {

                    }
                });
                break;
            case FRIDAY:
                repository.showPlanMealsByDay(Week.FRIDAY, new DataFetch<List<MealPlan>>() {
                    @Override
                    public void onDataSuccessResponse(List<MealPlan> data) {
                        if(data.isEmpty())
                        {
                            holder.emptyView.setVisibility(View.VISIBLE);
                        }
                        mealsAdapter=new MealsAdapter(context,data);
                        holder.mealsRecyclerView.setAdapter(mealsAdapter);

                        Log.i("RoomTAG", "onDataSuccessResponse:from room "+data.size());
                    }

                    @Override
                    public void onDataFailedResponse(String message) {

                    }

                    @Override
                    public void onDataLoading() {

                    }
                });
                break;
            default:

        }


    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dayTxtView;
        RecyclerView mealsRecyclerView;
        private TextView emptyView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTxtView=itemView.findViewById(R.id.dayTextView);
            mealsRecyclerView=itemView.findViewById(R.id.mealsRecycleView);
            emptyView = itemView.findViewById(R.id.empty_view);

        }
    }
}

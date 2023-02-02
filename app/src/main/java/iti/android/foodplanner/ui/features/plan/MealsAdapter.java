package iti.android.foodplanner.ui.features.plan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealPlan;
import iti.android.foodplanner.ui.features.home.HomeFragmentDirections;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder>{
    private List<MealPlan> values;
    private Context context;

    Repository repository;
    public MealsAdapter(Context context, List<MealPlan> dataset) {
        this.context = context;
        values = dataset;
        repository=Repository.getInstance(context);

    }
    @NonNull
    @Override
    public MealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.meals_row,parent,false);


        return new MealsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsAdapter.ViewHolder holder, int position) {

        holder.mealName.setText(values.get(position).getStrMeal());
        holder.mealCountry.setText(values.get(position).getStrArea());
        holder.mealCategory.setText(values.get(position).getStrCategory());
        Glide.with(context).
                load(values.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(400,300).
                        placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)).
                into(holder.mealThumb);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                repository.deletePlanMeal(values.get(position), new DataFetch<Void>() {
                    @Override
                    public void onDataSuccessResponse(Void data) {
                            values.remove(position);
                            notifyItemRemoved(position);


                    }

                    @Override
                    public void onDataFailedResponse(String message) {

                    }

                    @Override
                    public void onDataLoading() {

                    }
                });
            }
        });
        holder.mealRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 PlanFragmentDirections.ActionNavigationPlanToNavigationDetails action=PlanFragmentDirections.actionNavigationPlanToNavigationDetails();
                action.setMealId(values.get(position).getIdMeal());
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mealName;
        TextView mealCategory;
        TextView mealCountry;
        ImageView deleteButton;
        ImageView mealThumb;
        LinearLayout mealRow;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName=itemView.findViewById(R.id.mealName);
            mealCategory=itemView.findViewById(R.id.categoryTxtView);
            mealCountry=itemView.findViewById(R.id.countryTxtView);
            deleteButton=itemView.findViewById(R.id.removeFromPlanButton);
            mealThumb=itemView.findViewById(R.id.mealImgView);
            mealRow=itemView.findViewById(R.id.row_mealPlan);

        }
    }
}

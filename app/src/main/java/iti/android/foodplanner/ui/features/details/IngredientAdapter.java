package iti.android.foodplanner.ui.features.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.room.Week;
import iti.android.foodplanner.ui.features.plan.DaysAdapter;
import iti.android.foodplanner.ui.features.plan.MealsAdapter;

public class IngredientAdapter  extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{
    static Context context;
    private List<String> values;
    Repository repository;

    public IngredientAdapter(Context context,List<String> dataset) {
        this.context = context;
        values=dataset;
        repository=Repository.getInstance(context);
    }

    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.ingredients_row,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        holder.ingName.setText(values.get(position));
        Glide.with(context).load(String.format("https://www.themealdb.com/images/ingredients/%s-Small.png",values.get(position)))
                .placeholder(R.drawable.ic_launcher_foreground).into(holder.ingThumb);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ingName;
        ImageView ingThumb;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ingName=itemView.findViewById(R.id.ingradientName);
            ingThumb=itemView.findViewById(R.id.ingradientImgView);

        }
    }
}

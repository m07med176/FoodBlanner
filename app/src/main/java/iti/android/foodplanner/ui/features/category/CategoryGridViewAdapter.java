package iti.android.foodplanner.ui.features.category;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.meal.MealsItem;

public class CategoryGridViewAdapter extends RecyclerView.Adapter<CategoryGridViewAdapter.ViewHolder> {
    private static final String TAG="RECYCLERVIEW";
    private final Context context;
    private List<MealsItem> mealsItemList;
    private CategoryInterface categoryInterface;


    public void setMealsItemList(List<MealsItem> mealsItemList) {
        this.mealsItemList = mealsItemList;
    }

    public List<MealsItem> getMealsItemList() {
        return mealsItemList;
    }

    public CategoryGridViewAdapter(Context context, List<MealsItem> dataSet,CategoryInterface categoryInterface){
        this.context=context;
        this.categoryInterface = categoryInterface;
        mealsItemList =dataSet;

    }

    @NonNull
    @Override
    public CategoryGridViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.row_category_search,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsItem mealsItem= mealsItemList.get(position);
        holder.foodName.setText(mealsItem.getStrMeal());
        holder.constraintLayout.setOnClickListener(view -> categoryInterface.onClickItem());

        Glide.with(context).
                load(mealsItem.getStrMealThumb())
                .apply(new RequestOptions()
                        .override(400,300)
                        .placeholder(R.drawable.shippingback)
                        .error(R.drawable.ic_close_black_24dp))
                        .into(holder.imageViewThumnail);



    }

    @Override
    public int getItemCount() {
        return mealsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView foodName;
        ImageView imageViewThumnail;
        ConstraintLayout constraintLayout;
        View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout=itemView;
            imageViewThumnail =itemView.findViewById(R.id.imgv_thumnail);
            foodName=itemView.findViewById(R.id.tv_name);
            constraintLayout=itemView.findViewById(R.id.constrainLayout);


                }
    }




}
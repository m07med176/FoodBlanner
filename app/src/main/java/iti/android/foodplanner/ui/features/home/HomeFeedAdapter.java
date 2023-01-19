package iti.android.foodplanner.ui.features.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.meal.MealsItem;

public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.ViewHolder> {
    private List<MealsItem> itemsList;
    private Context context;
    HomeInterface homeInterface;

    public HomeFeedAdapter(Context context, List<MealsItem> itemsList, HomeInterface homeInterface) {
        this.itemsList = itemsList;
        this.context = context;
        this.homeInterface = homeInterface;
    }

    @NonNull
    @Override
    public HomeFeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_menu,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFeedAdapter.ViewHolder holder, int position) {
        MealsItem item = itemsList.get(position);
        holder.foodNameTv.setText(item.getStrMeal());

        Glide.with(context)
                .load(item.getStrMealThumb())
                .apply(new RequestOptions()
                .override(400,300)
                .placeholder(R.drawable.shippingback)
                .error(R.drawable.ic_close_black_24dp))
                .into(holder.thumnailView);

        holder.addToPlaneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    homeInterface.onSavePlane(item);
            }
        });

        holder.addToFavBtn.setOnClickListener(view -> {
                    homeInterface.onSaveFavorite(item);
        });


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageButton addToPlaneBtn,addToFavBtn;
        TextView foodNameTv;
        ImageView thumnailView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addToPlaneBtn = itemView.findViewById(R.id.plane_btn);
            addToFavBtn = itemView.findViewById(R.id.fav_btn);
            thumnailView = itemView.findViewById(R.id.image_thum);
            foodNameTv = itemView.findViewById(R.id.food_name);
        }
    }


}

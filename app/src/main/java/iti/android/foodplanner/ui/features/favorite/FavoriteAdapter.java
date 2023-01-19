package iti.android.foodplanner.ui.features.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.meal.MealsItem;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<MealsItem> itemsList;
    private Context context;
    FavoriteAdapter.FavoriteAdapterActions favoriteAdapterActions;

    public FavoriteAdapter(Context context,List<MealsItem> itemsList, FavoriteAdapter.FavoriteAdapterActions favoriteAdapterActions) {
        this.itemsList = itemsList;
        this.context = context;
        this.favoriteAdapterActions = favoriteAdapterActions;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fav,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        MealsItem item = itemsList.get(position);
        holder.tvName.setText(item.getStrMeal());
        holder.tvName.setText(item.getStrCategory());
        Glide.with(context).load(item.getStrMealThumb()).into(holder.imagefav);
        holder.addToPlaneBtn.setOnClickListener(view -> {
            Toast.makeText(context, "Add This to Plan", Toast.LENGTH_SHORT).show();
        });
        holder.removeBtn.setOnClickListener(view -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle(context.getResources().getString(R.string.delete_title_dialog));
                alertDialog.setMessage(context.getResources().getString(R.string.delete_favorite_dialog));
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton(context.getResources().getString(R.string.dialog_positive_button), (dialog, which) -> favoriteAdapterActions.onCardClicked(item,position));
                alertDialog.setNegativeButton(context.getResources().getString(R.string.dialog_negative_button), (dialog, which) -> dialog.cancel());

                AlertDialog dialog = alertDialog.create();
                dialog.show();

        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvCategory;
        AppCompatButton addToPlaneBtn,removeBtn;
        ImageView imagefav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.food_name_tv);
            imagefav = itemView.findViewById(R.id.imagefav);
            tvCategory = itemView.findViewById(R.id.category_tv);
            addToPlaneBtn = itemView.findViewById(R.id.btn_plane);
            removeBtn = itemView.findViewById(R.id.deletebutton);

        }
    }

    public interface FavoriteAdapterActions{
        public void onCardClicked(MealsItem item,int position);
    }
}

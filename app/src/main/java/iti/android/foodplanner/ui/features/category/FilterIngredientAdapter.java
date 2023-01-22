package iti.android.foodplanner.ui.features.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.selections.Ingredient.Ingredient;

public class FilterIngredientAdapter extends RecyclerView.Adapter<FilterIngredientAdapter.ViewHolder> {
    private List<Ingredient> itemsList = new ArrayList<>();
    private Context context;
    CategoryInterface categoryInterface;

    public FilterIngredientAdapter(Context context, CategoryInterface categoryInterface) {
        this.context = context;
        this.categoryInterface = categoryInterface;
    }

    @NonNull
    @Override
    public FilterIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter_ingredient_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilterIngredientAdapter.ViewHolder holder, int position) {
        Ingredient item = itemsList.get(position);
        holder.title.setText(item.getStrIngredient());

        Glide.with(context)
                .load(item.getThumnail())
                .apply(new RequestOptions()
                .override(400,300)
                .placeholder(R.drawable.shippingback)
                .error(R.drawable.ic_close_black_24dp))
                .into(holder.thumnailView);



    }

    public void setItemsList(List<Ingredient> itemsList){
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView thumnailView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            thumnailView = itemView.findViewById(R.id.profile_image);
        }
    }


}

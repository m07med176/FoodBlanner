package iti.android.foodplanner.ui.features.category.adpaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.selections.Ingredient.Ingredient;
import iti.android.foodplanner.ui.features.category.CategoryInterface;
import iti.android.foodplanner.ui.util.Utils;

public class FilterIngredientAdapter extends RecyclerView.Adapter<FilterIngredientAdapter.ViewHolder> {
    private List<Ingredient> itemsList = new ArrayList<>();
    public MutableLiveData<Boolean> isHaveData = new MutableLiveData<Boolean>(false);

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


        Utils.loadImage(context,item.getThumnail(),holder.thumnailView);




    }

    public void setItemsList(List<Ingredient> itemsList){
        this.itemsList = itemsList;
        notifyDataSetChanged();
        isHaveData.postValue(itemsList.size()>0); // to notify if there is a data

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

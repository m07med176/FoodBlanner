package iti.android.foodplanner.ui.features.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.ui.features.home.HomeFragmentDirections;
import iti.android.foodplanner.ui.util.Utils;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private List<MealsItem> itemsList = new ArrayList<>();
    private Context context;
    private SearchInterface searchInterface;

    public SearchResultAdapter(Context context, SearchInterface searchInterface) {
        this.context = context;
        this.searchInterface = searchInterface;
    }

    @NonNull
    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meals_search_result_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultAdapter.ViewHolder holder, int position) {
        MealsItem item = itemsList.get(position);
        holder.foodNameTv.setText(item.getStrMeal());


        Utils.loadImage(context,item.getStrMealThumb(),holder.thumnailView);



        holder.addToPlaneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    searchInterface.onSavePlane(item);
            }
        });

        holder.addToFavBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    searchInterface.onSaveFavorite(item);
            }
        });

        holder.itemHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeFragmentDirections.ActionNavigationHomeToNavigationDetails action=HomeFragmentDirections.actionNavigationHomeToNavigationDetails();
                action.setMealId(item.getIdMeal());
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    public void setItemsList(List<MealsItem> itemsList){
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        AppCompatButton addToPlaneBtn;
        CheckBox addToFavBtn;
        TextView foodNameTv;
        ImageView thumnailView;
        RelativeLayout itemHome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            addToPlaneBtn = itemView.findViewById(R.id.addTOPlanButton);
            foodNameTv = itemView.findViewById(R.id.tv_title);
            addToFavBtn = itemView.findViewById(R.id.fav_ceheck);
            thumnailView = itemView.findViewById(R.id.thumnail_image);
            itemHome=itemView.findViewById(R.id.itemHome);
        }
    }


}

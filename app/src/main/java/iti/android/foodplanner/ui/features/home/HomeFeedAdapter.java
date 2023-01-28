package iti.android.foodplanner.ui.features.home;

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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.ui.util.Utils;

public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.ViewHolder> {
    private List<MealsItem> itemsList = new ArrayList<>();
    public MutableLiveData<Boolean> isHaveData = new MutableLiveData<Boolean>(false);
    private HomePresenter presenter;

    private Context context;
    private HomeInterface homeInterface;

    public HomeFeedAdapter(Context context, HomeInterface homeInterface) {
        presenter = new HomePresenter(context,homeInterface);
        this.context = context;
        this.homeInterface = homeInterface;
    }

    @NonNull
    @Override
    public HomeFeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meals_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFeedAdapter.ViewHolder holder, int position) {
        MealsItem item = itemsList.get(position);
        holder.foodNameTv.setText(item.getStrMeal());


        Utils.loadImage(context,item.getStrMealThumb(),holder.thumnailView);

        if (!presenter.isUser){
            holder.addToPlaneBtn.setVisibility(View.GONE);
            holder.addToFavBtn.setVisibility(View.GONE);
        }


        holder.addToPlaneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                homeInterface.onSavePlane(item);
            }
        });

        holder.addToFavBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    homeInterface.onSaveFavorite(item);
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
        isHaveData.postValue(itemsList.size()>0); // to notify if there is a data
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

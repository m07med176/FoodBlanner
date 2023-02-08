package iti.android.foodplanner.ui.features.home;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.ui.util.Utils;

public class HomeFeedAdapter extends RecyclerView.Adapter<HomeFeedAdapter.ViewHolder> {
    private List<MealsItem> itemsList = new ArrayList<>();
    public MutableLiveData<Boolean> isHaveData = new MutableLiveData<Boolean>(false);
    private HomePresenter presenter;
    private final static String TAG ="SHAREDISUSER";
    private Context context;
    private Activity activity;
    private HomeInterface homeInterface;

    public HomeFeedAdapter(Context context, HomeInterface homeInterface,Activity activity) {
        presenter = new HomePresenter(context,homeInterface);
        this.context = context;
        this.activity = activity;
        this.homeInterface = homeInterface;
    }

    @NonNull
    @Override
    public HomeFeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      ViewHolder v=new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meals_list,parent,false));

        return  v;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFeedAdapter.ViewHolder holder, int position) {

        MealsItem item = itemsList.get(position);

        presenter.isFound(itemsList.get(position).getIdMeal(), new DataFetch<Boolean>() {
            @Override
            public void onDataSuccessResponse(Boolean data) {
                if(data)
                {
                    if (!presenter.isUser){
                        Log.i(TAG, "onCreateViewHolder: ");
                        holder.addToPlaneBtn.setVisibility(View.GONE);
                        holder.addToFavBtn.setVisibility(View.GONE);
                    }
                    holder.addToFavBtn.setChecked(true);
                    Log.i("TAG", "onDataSuccessResponse: addToFavButton"+data.toString());
                }
            }

            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });

        holder.foodNameTv.setText(item.getStrMeal());


        Utils.loadImage(context,item.getStrMealThumb(),holder.thumnailView);



        holder.addToPlaneBtn.setOnClickListener(view ->

                homeInterface.onSavePlane(item));

        holder.addToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.addToFavBtn.isChecked())
                    presenter.saveFavorite(item, new DataFetch<Void>() {
                        @Override
                        public void onDataSuccessResponse(Void data) {
                            ConstraintLayout constraintLayout = activity.getWindow().getDecorView().findViewById(R.id.container);
                            Utils.snakeMessage(
                                    context,
                                    constraintLayout,
                                    item.getStrMeal() + "Has been saved into favorite",
                                    true,
                                    "SEE FAVORITE",
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Navigation.findNavController(v).navigate(R.id.navigation_favorite);
                                        }
                                    }
                            ).show();
                        }

                        @Override
                        public void onDataFailedResponse(String message) {

                        }

                        @Override
                        public void onDataLoading() {

                        }
                    });
                else
                    presenter.deleteFavorite(item, new DataFetch<Void>() {
                        @Override
                        public void onDataSuccessResponse(Void data) {
                            ConstraintLayout constraintLayout = activity.getWindow().getDecorView().findViewById(R.id.container);
                            Utils.snakeMessage(
                                    context,
                                    constraintLayout,
                                    item.getStrMeal() + "Has been removed into favorite",
                                    false,
                                    "SEE FAVORITE",
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Navigation.findNavController(v).navigate(R.id.navigation_favorite);
                                        }
                                    }
                            ).show();
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

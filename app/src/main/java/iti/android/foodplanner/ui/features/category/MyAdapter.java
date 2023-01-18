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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final Context context;
    private List<MealsItem> values;
    
    private static final String TAG="RECYCLEVIEW";


    public void setValues(List<MealsItem> values) {
        this.values = values;
    }

    public List<MealsItem> getValues() {
        return values;
    }

    public MyAdapter (Context context, List<MealsItem> dataSet){
        this.context=context;
        values=dataSet;

    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.row_category_search,parent,false);
        ViewHolder vh=new ViewHolder(v);
        Log.i(TAG, "======onCreateViewHolder: ==========");

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealsItem MealsItem=values.get(position);
        holder.descriptionTxtView.setText("Price : "+values.get(position).getStrMeal());

        Glide.with(context).
                load(values.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(400,300).
                        placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground)).
                into(holder.imgView);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,values.get(position).toString(),Toast.LENGTH_SHORT).show();

            }
        });

        Log.i(TAG, "###############onBindViewHolder: ###########");

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView descriptionTxtView;
        ImageView imgView;
        ConstraintLayout constraintLayout;
        View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout=itemView;
            descriptionTxtView=itemView.findViewById(R.id.descriptionTxtView);
            imgView=itemView.findViewById(R.id.shortMealImgView);
            constraintLayout=itemView.findViewById(R.id.constrainLayout);


                }
    }




}
package iti.android.foodplanner.ui.features.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.selections.area.Area;

public class FilterAreaAdapter extends RecyclerView.Adapter<FilterAreaAdapter.ViewHolder> {
    private List<Area> itemsList = new ArrayList<>();
    private Context context;
    CategoryInterface categoryInterface;

    public FilterAreaAdapter(Context context, CategoryInterface categoryInterface) {
        this.context = context;
        this.categoryInterface = categoryInterface;
    }

    @NonNull
    @Override
    public FilterAreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter_area_and_category_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilterAreaAdapter.ViewHolder holder, int position) {
        Area item = itemsList.get(position);
        holder.title.setText(item.getStrArea());

        Glide.with(context)
                .load(item.getThumbnail())
                .apply(new RequestOptions()
                .override(400,300)
                .placeholder(R.drawable.shippingback)
                .error(R.drawable.ic_close_black_24dp))
                .into(holder.thumnailView);



    }

    public void setItemsList(List<Area> itemsList){
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

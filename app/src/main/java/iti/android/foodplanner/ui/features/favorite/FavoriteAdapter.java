package iti.android.foodplanner.ui.features.favorite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iti.android.foodplanner.R;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    String[] names;
    FavoriteAdapter.FavoriteAdapterActions favoriteAdapterActions;

    public FavoriteAdapter(String[] names,FavoriteAdapter.FavoriteAdapterActions favoriteAdapterActions) {
        this.names = names;
        this.favoriteAdapterActions = favoriteAdapterActions;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        String name = names[position];
        holder.tvName.setText(name);

        // TODO Change Source of Event
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteAdapterActions.onCardClicked(/*TODO pass Parameter if needed*/);
            }
        });

    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public interface FavoriteAdapterActions{
        public void onCardClicked();
    }
}

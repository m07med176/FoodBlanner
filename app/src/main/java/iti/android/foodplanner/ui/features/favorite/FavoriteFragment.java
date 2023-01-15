package iti.android.foodplanner.ui.features.favorite;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import iti.android.foodplanner.databinding.FragmentFavoriteBinding;
import iti.android.foodplanner.ui.util.Utils;


public class FavoriteFragment extends Fragment implements FavoriteInterface{
    private static final String TAG = "FavoriteFragment";
    private FragmentFavoriteBinding binding;
    private FavoritePresenter presenter;
    private FavoriteAdapter favoriteAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = Utils.recyclerViewHandler(binding.rvListFavorite,getContext());

        presenter = new FavoritePresenter(getContext(),this);
        favoriteAdapter = new FavoriteAdapter(presenter.getData(), () -> {
            // Do Action On CLick Item
            Log.d(TAG, "onCreateView: "+"Do Action On CLick Item");
        });

        recyclerView.setAdapter(favoriteAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
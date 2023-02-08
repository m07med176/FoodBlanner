package iti.android.foodplanner.ui.features.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.databinding.FragmentFavoriteBinding;
import iti.android.foodplanner.ui.features.login.LoginActivity;
import iti.android.foodplanner.ui.util.Utils;


public class FavoriteFragment extends Fragment implements FavoriteInterface{
    private static final String TAG = "FavoriteFragment";
    private FragmentFavoriteBinding binding;
    private FavoritePresenter presenter;
    private FavoriteAdapter favoriteAdapter;
    private List<MealsItem> mealsItemList = new ArrayList<>();
    private  RecyclerView recyclerView;
    private ConstraintLayout constraintLayout;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        presenter = new FavoritePresenter(getContext(),this);
        constraintLayout = getActivity().getWindow().getDecorView().findViewById(R.id.container);
        View view = binding.getRoot();
        if (!presenter.isUser){
            getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
            Toast.makeText(getContext(), "Please Login First or Register", Toast.LENGTH_SHORT).show();
            return view;
        }
        recyclerViewHandle();
        presenter.getFavorites(this);
        return view;
    }

    private void recyclerViewHandle() {
        recyclerView = Utils.recyclerViewHandler(binding.rvListFavorite,getContext());
        favoriteAdapter = new FavoriteAdapter(getContext(),getActivity(), mealsItemList, (item, position) -> {
            presenter.removeFavorite(item, new DataFetch<Void>() {
                @Override
                public void onDataSuccessResponse(Void data) {
                    mealsItemList.remove(item);
                    favoriteAdapter.notifyItemRemoved(position);
                    ConstraintLayout constraintLayout = getActivity().getWindow().getDecorView().findViewById(R.id.container);

                    Utils.snakeMessage(
                            getContext(),
                            constraintLayout,
                            item.getStrMeal() + "Has been removed from favorite",
                            false).show();

                    if (mealsItemList.size() == 0)
                        showNoData();


                }

                @Override
                public void onDataFailedResponse(String message) {
                    Utils.snakeMessage(
                            getContext(),
                            constraintLayout,
                            "Problem happened during delete "+message,
                            false
                    ).show();
                }

                @Override
                public void onDataLoading() {

                }
            });
        });
        recyclerView.setAdapter(favoriteAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!presenter.isUser)
            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_home);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!presenter.isUser)
            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_home);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onDataSuccessResponse(List<MealsItem> data) {
        if (data.size() == 0){
            showNoData();
        }else{
            showData();
            mealsItemList.clear();
            mealsItemList.addAll(data);
            favoriteAdapter.notifyDataSetChanged();
        }

    }

    private void showNoData() {
        binding.rvListFavorite.setVisibility(View.GONE);
        binding.noDataHolder.setVisibility(View.VISIBLE);
        binding.noNetworkHolder.setVisibility(View.GONE);
        binding.shimmerHolder.setVisibility(View.GONE);

    }

    private void showData() {
        binding.rvListFavorite.setVisibility(View.VISIBLE);
        binding.noDataHolder.setVisibility(View.GONE);
        binding.shimmerHolder.setVisibility(View.GONE);
        binding.noNetworkHolder.setVisibility(View.GONE);
    }

    private void showError() {
        binding.rvListFavorite.setVisibility(View.GONE);
        binding.noDataHolder.setVisibility(View.GONE);
        binding.shimmerHolder.setVisibility(View.GONE);
        binding.noNetworkHolder.setVisibility(View.VISIBLE);

    }

    private void showShimmer() {
        binding.rvListFavorite.setVisibility(View.GONE);
        binding.noDataHolder.setVisibility(View.GONE);
        binding.noNetworkHolder.setVisibility(View.GONE);
        binding.shimmerHolder.setVisibility(View.VISIBLE);

    }


    @Override
    public void onDataFailedResponse(String message) {
        showError();
        Utils.snakeMessage(
                getContext(),
                constraintLayout,
                "Problem happened during delete "+message,
                false
        ).show();    }

    @Override
    public void onDataLoading() {
        showShimmer();
    }
}
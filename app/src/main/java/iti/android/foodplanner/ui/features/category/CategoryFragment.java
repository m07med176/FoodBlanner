package iti.android.foodplanner.ui.features.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.models.selections.Ingredient.Ingredient;
import iti.android.foodplanner.data.models.selections.area.Area;
import iti.android.foodplanner.data.models.selections.category.Category;
import iti.android.foodplanner.databinding.FragmentCategoryBinding;
import iti.android.foodplanner.ui.features.category.adpaters.FilterAreaAdapter;
import iti.android.foodplanner.ui.features.category.adpaters.FilterCategoryAdapter;
import iti.android.foodplanner.ui.features.category.adpaters.FilterIngredientAdapter;
import iti.android.foodplanner.ui.features.search.SearchInterface;
import iti.android.foodplanner.ui.util.Utils;

public class CategoryFragment extends Fragment implements CategoryInterface {

    private CategoryPresenter categoryPresenter;

    private FragmentCategoryBinding binding;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryPresenter = new CategoryPresenter(getContext());
        recyclerViewCategorySettings();
        recyclerViewAreaSettings();
        recyclerViewIngredientSettings();
        navigateToSeeMore();


    }

    private void navigateToSeeMore() {
        binding.seeMoreArea.setOnClickListener(view -> {
            Utils.navigatorCategoryToSearchFragment(view, SearchInterface.AREA,"");
        });
        binding.seeMoreCategory.setOnClickListener(view -> {
            Utils.navigatorCategoryToSearchFragment(view,SearchInterface.CATEGORY,"");
        });
        binding.seeMoreIngredient.setOnClickListener(view -> {
            Utils.navigatorCategoryToSearchFragment(view,SearchInterface.INGREDIENT,"");
        });
        binding.searchView.setOnClickListener(view -> {
            Utils.navigatorCategoryToSearchFragment(view,SearchInterface.SEARCH,"");
        });

    }


    private void recyclerViewAreaSettings() {
        RecyclerView rvArea = Utils.recyclerViewHandler(binding.rvAreas,getContext());
        FilterAreaAdapter filterAreaAdapter = new FilterAreaAdapter(getContext(),this);
        rvArea.setAdapter(filterAreaAdapter);
        categoryPresenter.getFilterAreaResults(new DataFetch<List<Area>>() {
            @Override
            public void onDataSuccessResponse(List<Area> data) {
                filterAreaAdapter.setItemsList(data);
                binding.shimmerSaerchArea.setVisibility(View.GONE);
                binding.rvAreas.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDataFailedResponse(String message) {
                binding.shimmerSaerchArea.setVisibility(View.VISIBLE);
                binding.rvAreas.setVisibility(View.GONE);
            }

            @Override
            public void onDataLoading() {
                binding.shimmerSaerchArea.setVisibility(View.VISIBLE);
                binding.rvAreas.setVisibility(View.GONE);
            }
        });



    }

    private void recyclerViewIngredientSettings() {
        RecyclerView rvIngredient = Utils.recyclerViewHandler(binding.rvIngredient,getContext());
        FilterIngredientAdapter filterIngredientAdapter = new FilterIngredientAdapter(getContext(),this);
        rvIngredient.setAdapter(filterIngredientAdapter);
        categoryPresenter.getFilterIngredientResults(new DataFetch<List<Ingredient>>() {
            @Override
            public void onDataSuccessResponse(List<Ingredient> data) {
                filterIngredientAdapter.setItemsList(data);
                binding.shimmerSaerchIngredient.setVisibility(View.GONE);
                binding.rvIngredient.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDataFailedResponse(String message) {
                binding.shimmerSaerchIngredient.setVisibility(View.VISIBLE);
                binding.rvIngredient.setVisibility(View.GONE);
            }

            @Override
            public void onDataLoading() {
                binding.shimmerSaerchIngredient.setVisibility(View.VISIBLE);
                binding.rvIngredient.setVisibility(View.GONE);
            }
        });
    }

    private void recyclerViewCategorySettings() {
        RecyclerView rvCategory = Utils.recyclerViewHandler(binding.rvCategory,getContext());
        FilterCategoryAdapter filterCategoryAdapter = new FilterCategoryAdapter(getContext(),this);
        rvCategory.setAdapter(filterCategoryAdapter);

        categoryPresenter.getFilterCategoryResults(new DataFetch<List<Category>>() {
            @Override
            public void onDataSuccessResponse(List<Category> data) {
                filterCategoryAdapter.setItemsList(data);
                binding.shimmerSaerchCategory.setVisibility(View.GONE);
                binding.rvCategory.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDataFailedResponse(String message) {
                binding.shimmerSaerchCategory.setVisibility(View.VISIBLE);
                binding.rvCategory.setVisibility(View.GONE);
            }

            @Override
            public void onDataLoading() {
                binding.shimmerSaerchCategory.setVisibility(View.VISIBLE);
                binding.rvCategory.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClickItem() {
        Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}
package iti.android.foodplanner.ui.features.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.data.models.selections.Ingredient.Ingredient;
import iti.android.foodplanner.data.models.selections.area.Area;
import iti.android.foodplanner.data.models.selections.category.Category;
import iti.android.foodplanner.databinding.FragmentCategoryBinding;
import iti.android.foodplanner.ui.util.Utils;

public class CategoryFragment extends Fragment implements CategoryInterface {
    private CategoryPresenter categoryPresenter;
    private FragmentCategoryBinding binding;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryPresenter = new CategoryPresenter(getContext());
        RecyclerView rvArea = Utils.recyclerViewHandler(binding.rvAreas,getContext());
        RecyclerView rvCategory = Utils.recyclerViewHandler(binding.rvCategory,getContext());
        RecyclerView rvIngredient = Utils.recyclerViewHandler(binding.rvIngredient,getContext());

        FilterCategoryAdapter filterCategoryAdapter = new FilterCategoryAdapter(getContext(),this);
        FilterAreaAdapter filterAreaAdapter = new FilterAreaAdapter(getContext(),this);
        FilterIngredientAdapter filterIngredientAdapter = new FilterIngredientAdapter(getContext(),this);

        rvArea.setAdapter(filterAreaAdapter);
        rvIngredient.setAdapter(filterIngredientAdapter);
        rvCategory.setAdapter(filterCategoryAdapter);

        categoryPresenter.getFilterAreaResults(new DataFetch<List<Area>>() {
            @Override
            public void onDataSuccessResponse(List<Area> data) {
                filterAreaAdapter.setItemsList(data);
            }

            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });


        categoryPresenter.getFilterCategoryResults(new DataFetch<List<Category>>() {
            @Override
            public void onDataSuccessResponse(List<Category> data) {
                filterCategoryAdapter.setItemsList(data);
            }

            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

            }
        });
        categoryPresenter.getFilterIngredientResults(new DataFetch<List<Ingredient>>() {
            @Override
            public void onDataSuccessResponse(List<Ingredient> data) {
                filterIngredientAdapter.setItemsList(data);
            }

            @Override
            public void onDataFailedResponse(String message) {

            }

            @Override
            public void onDataLoading() {

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
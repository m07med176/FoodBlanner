package iti.android.foodplanner.ui.features.search;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.DataFetch;
import iti.android.foodplanner.data.models.meal.MealsItem;
import iti.android.foodplanner.databinding.FragmentSearchBinding;
import iti.android.foodplanner.ui.features.AddToPlanDialog.AddToPlanDailog;
import iti.android.foodplanner.ui.features.home.HomeFeedAdapter;
import iti.android.foodplanner.ui.features.home.HomePresenter;
import iti.android.foodplanner.ui.util.Utils;

public class SearchFragment extends Fragment implements SearchInterface{

    private FragmentSearchBinding binding;
    private SearchPresenter presenter;
    private RecyclerView rvSearch;
    private SearchResultAdapter searchResultAdapter;
    private AddToPlanDailog addToPlanDailog;

    private int type;
    private String query=  "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater,container,false);
        presenter = new SearchPresenter(getContext(),this);
        addToPlanDailog=new AddToPlanDailog(requireContext());
        rvSearch = Utils.recyclerViewHandler(binding.rvSearch, getContext());
        searchResultAdapter = new SearchResultAdapter(getContext(), this);
        rvSearch.setAdapter(searchResultAdapter);

        type = SearchFragmentArgs.fromBundle(getArguments()).getType();
        query = SearchFragmentArgs.fromBundle(getArguments()).getSearch();
        Utils.setAutoCompleteCash(getContext(), presenter.getCashList(type), binding.searchView);


        checkQueryType();
        return binding.getRoot();
    }

    private void checkQueryType() {
        if (!query.isEmpty()){
            binding.searchView.setText(query);
            presenter.getSearchResultMeals(type,query);
        }else{
            presenter.getSearchResultMeals(type, presenter.getRandomList(type));
        }

        if (type == SEARCH){
            binding.searchView.requestFocus();
            binding.searchView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    presenter.getSearchResultMeals(type,charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }else{
            binding.searchView.showDropDown();
            binding.searchView.setOnItemClickListener((adapterView, view, i, l) -> {
                query = binding.searchView.getText().toString().trim();
                if (!query.isEmpty())
                    presenter.getSearchResultMeals(type,query);
            });

            binding.searchView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            if (charSequence.toString().trim().isEmpty())
                                binding.searchView.showDropDown();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }


    }


    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }


    @Override
    public void onDataSuccessResponse(List<MealsItem> data) {

        if (data.size()==0){
            binding.rvSearch.setVisibility(View.GONE);
            binding.shimmerHolder.setVisibility(View.GONE);
            binding.noNetworkHolder.setVisibility(View.GONE);
            binding.noDataHolder.setVisibility(View.VISIBLE);
        }else{
            binding.rvSearch.setVisibility(View.VISIBLE);
            binding.shimmerHolder.setVisibility(View.GONE);
            binding.noNetworkHolder.setVisibility(View.GONE);
            binding.noDataHolder.setVisibility(View.GONE);
            searchResultAdapter.setItemsList(data);
        }

    }

    @Override
    public void onDataFailedResponse(String message) {
        binding.rvSearch.setVisibility(View.GONE);
        binding.shimmerHolder.setVisibility(View.GONE);
        binding.noNetworkHolder.setVisibility(View.VISIBLE);
        binding.noDataHolder.setVisibility(View.GONE);
    }

    @Override
    public void onDataLoading() {
        binding.rvSearch.setVisibility(View.GONE);
        binding.shimmerHolder.setVisibility(View.VISIBLE);
        binding.noNetworkHolder.setVisibility(View.GONE);
        binding.noDataHolder.setVisibility(View.GONE);
    }

    @Override
    public void onSavePlane(MealsItem item) {

        addToPlanDailog.createDialog(item.convertMealsItemToMealsPlan(item),requireContext());

    }

    @Override
    public void onSaveFavorite(MealsItem item) {
        presenter.saveFavorite(item, new DataFetch<Void>() {
            @Override
            public void onDataSuccessResponse(Void data) {
                Toast.makeText(getContext(), item.getStrMeal() + " Added To Favorite", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDataFailedResponse(String message) {
                Toast.makeText(getContext(), " Error Happened: " + message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDataLoading() {

            }
        });
    }

    @Override
    public void onDeleteFavorite(MealsItem item,DataFetch<Void>dataFetch) {
        presenter.deleteFavorite(item,dataFetch);
    }


}
package iti.android.foodplanner.ui.features.search;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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
import iti.android.foodplanner.ui.features.home.HomeFeedAdapter;
import iti.android.foodplanner.ui.features.home.HomePresenter;
import iti.android.foodplanner.ui.util.Utils;

public class SearchFragment extends Fragment implements SearchInterface{

    private FragmentSearchBinding binding;
    private SearchPresenter presenter;
    private RecyclerView rvSearch;
    private SearchResultAdapter searchResultAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater,container,false);
        presenter = new SearchPresenter(getContext(),this);
        rvSearch = Utils.recyclerViewHandler(binding.rvSearch, getContext());
        searchResultAdapter = new SearchResultAdapter(getContext(), this);
        rvSearch.setAdapter(searchResultAdapter);

        recycleriewIngredientsSettings();
        return binding.getRoot();
    }


    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }

    private void recycleriewIngredientsSettings() {
        presenter.getSearchResultMeals(SearchPresenter.AREA, "Egyptian");
    }


    @Override
    public void onDataSuccessResponse(List<MealsItem> data) {
        binding.rvSearch.setVisibility(View.VISIBLE);
        binding.shimmerHolder.setVisibility(View.GONE);
        binding.noNetworkHolder.setVisibility(View.GONE);
        searchResultAdapter.setItemsList(data);
    }

    @Override
    public void onDataFailedResponse(String message) {
        binding.rvSearch.setVisibility(View.GONE);
        binding.shimmerHolder.setVisibility(View.GONE);
        binding.noNetworkHolder.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDataLoading() {
        binding.rvSearch.setVisibility(View.GONE);
        binding.shimmerHolder.setVisibility(View.VISIBLE);
        binding.noNetworkHolder.setVisibility(View.GONE);
    }

    @Override
    public void onSavePlane(MealsItem item) {

    }

    @Override
    public void onSaveFavorite(MealsItem item) {

    }

    private void adjustAutoComplete() {
        // set data in autocomplete

        Utils.setAutoCompleteCash(getContext(),"".split(","), binding.searchView);
        // show result search

        binding.searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String search = binding.searchView.getText().toString().trim();
                if (!search.isEmpty()) {

                } else {
                    Toast.makeText(requireContext(), "يجب عليك كتابة رقم حساب العميل", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
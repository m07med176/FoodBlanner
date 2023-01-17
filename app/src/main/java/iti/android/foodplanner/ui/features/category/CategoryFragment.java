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

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.ShortMeals;

public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<ShortMeals>shortMeals;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shortMeals=new ArrayList<>();
        ShortMeals meals=new ShortMeals();
        meals.setStrMeal("moamen");
        ShortMeals meals1=new ShortMeals();
        meals.setStrMeal("moamen");
        ShortMeals meals2=new ShortMeals();
        meals.setStrMeal("moamen");
        ShortMeals meals3=new ShortMeals();
        meals.setStrMeal("moamen");
        ShortMeals meals4=new ShortMeals();
        meals.setStrMeal("moamen");
        ShortMeals meals5=new ShortMeals();
        meals.setStrMeal("moamen");
        ShortMeals meals6=new ShortMeals();
        meals.setStrMeal("moamen");
        shortMeals.add(meals);
        shortMeals.add(meals1);
        shortMeals.add(meals2);
        shortMeals.add(meals3);
        shortMeals.add(meals4);
        shortMeals.add(meals5);
        shortMeals.add(meals6);







    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter(getContext(),shortMeals);
        recyclerView.setAdapter(myAdapter);

    }
}
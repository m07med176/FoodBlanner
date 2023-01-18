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
import iti.android.foodplanner.data.models.meal.MealsItem;

public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    List<iti.android.foodplanner.data.models.meal.MealsItem>MealsItem;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MealsItem=new ArrayList<>();
        MealsItem meals=new MealsItem();
        meals.setStrMeal("moamen");
        MealsItem meals1=new MealsItem();
        meals.setStrMeal("moamen");
        MealsItem meals2=new MealsItem();
        meals.setStrMeal("moamen");
        MealsItem meals3=new MealsItem();
        meals.setStrMeal("moamen");
        MealsItem meals4=new MealsItem();
        meals.setStrMeal("moamen");
        MealsItem meals5=new MealsItem();
        meals.setStrMeal("moamen");
        MealsItem meals6=new MealsItem();
        meals.setStrMeal("moamen");
        MealsItem.add(meals);
        MealsItem.add(meals1);
        MealsItem.add(meals2);
        MealsItem.add(meals3);
        MealsItem.add(meals4);
        MealsItem.add(meals5);
        MealsItem.add(meals6);







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
        myAdapter = new MyAdapter(getContext(),MealsItem);
        recyclerView.setAdapter(myAdapter);

    }
}
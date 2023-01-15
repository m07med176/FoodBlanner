package iti.android.foodplanner.ui.util;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Utils {
    public static RecyclerView recyclerViewHandler(RecyclerView recyclerView, Context context){
        // Function to handle recyclerview and adjust its settings
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return recyclerView;
    }
}

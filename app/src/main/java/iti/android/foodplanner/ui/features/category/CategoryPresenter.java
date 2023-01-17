package iti.android.foodplanner.ui.features.category;

import android.content.Context;

import iti.android.foodplanner.data.Repository;

public class CategoryPresenter {
    Context context;
    Repository repository;

    public CategoryPresenter(Context context, Repository repository) {
        this.context = context;
        this.repository = repository;
    }
}

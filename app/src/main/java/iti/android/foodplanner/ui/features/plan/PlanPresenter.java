package iti.android.foodplanner.ui.features.plan;

import android.content.Context;

import iti.android.foodplanner.data.Repository;

public class PlanPresenter {

    public boolean isUser = false;
    private Repository repository;

    public PlanPresenter(Context context) {
        repository  = Repository.getInstance(context);
        isUser = repository.isUser();
    }
}

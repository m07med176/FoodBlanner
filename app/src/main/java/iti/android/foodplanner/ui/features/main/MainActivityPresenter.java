package iti.android.foodplanner.ui.features.main;

import android.content.Context;

import iti.android.foodplanner.data.Repository;
import iti.android.foodplanner.data.models.User;

public class MainActivityPresenter {
    Repository repository;

    public MainActivityPresenter(Context context) {
        repository = Repository.getInstance(context);
    }

    public User getUserData(){
        return repository.getUser();
    }

}

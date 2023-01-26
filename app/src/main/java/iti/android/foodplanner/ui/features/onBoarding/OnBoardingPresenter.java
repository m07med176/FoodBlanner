package iti.android.foodplanner.ui.features.onBoarding;

import android.content.Context;

import iti.android.foodplanner.data.Repository;

public class OnBoardingPresenter {
    Context context;
    Repository repository;
    public OnBoardingPresenter(Context context) {
        this.context = context;
        repository=Repository.getInstance(context);

    }
    public boolean isUser() {
        return repository.isUser();
    }

    // TODO save in shared preferance manager first entrance done
}

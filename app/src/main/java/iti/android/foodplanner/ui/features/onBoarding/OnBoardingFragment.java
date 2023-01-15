package iti.android.foodplanner.ui.features.onBoarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iti.android.foodplanner.R;

public class OnBoardingFragment extends Fragment {

    OnBoardingPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new OnBoardingPresenter(getContext());

        return inflater.inflate(R.layout.fragment_on_boarding, container, false);
    }

    // TODO Implements view pager or OnBoaridng Module
    //  see https://github.com/codemybrainsout/ahoy-onboarding

    // TODO save in shared preferance manager first entrance done
}
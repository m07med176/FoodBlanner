package iti.android.foodplanner.ui.features.details;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import iti.android.foodplanner.R;
import iti.android.foodplanner.databinding.FragmentDetailsBinding;
import iti.android.foodplanner.databinding.FragmentFavoriteBinding;

public class DetailsFragment extends Fragment {


    private FragmentDetailsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.tvDetails;

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
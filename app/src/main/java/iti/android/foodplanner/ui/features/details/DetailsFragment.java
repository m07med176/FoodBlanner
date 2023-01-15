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

public class DetailsFragment extends Fragment implements DetailsInterface{
    // TODO Implementing Video Player From Youtube
    //  see https://github.com/PierfrancescoSoffritti/android-youtube-player

    // TODO Implementing Loading Photo using GLIDE
    //  see https://github.com/bumptech/glide



    private FragmentDetailsBinding binding;
    private DetailsPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        presenter = new DetailsPresenter(getContext(),this);

        final TextView textView = binding.tvDetails;

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
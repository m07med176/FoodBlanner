package iti.android.foodplanner.ui.features.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import iti.android.foodplanner.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements HomeInterface{

    HomePresenter presenter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        presenter = new HomePresenter(getContext(),this);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // TODO retrieve data from Daily inspiration
        // TODO retrieve data from Categories as ArrayList in Recycler View Adapter
        final TextView textView = binding.textHome;
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
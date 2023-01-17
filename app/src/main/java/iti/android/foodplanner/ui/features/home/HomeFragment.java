package iti.android.foodplanner.ui.features.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import iti.android.foodplanner.R;
import iti.android.foodplanner.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements HomeInterface{

    HomePresenter presenter;
    private FragmentHomeBinding binding;
    ViewPager2 viewPager2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2=view.findViewById(R.id.homeViewPager);
        //viewPager2.setAdapter(new CustomPagerAdapter(this));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        presenter = new HomePresenter(getContext(),this);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // TODO retrieve data from Daily inspiration
        // TODO retrieve data from Categories as ArrayList in Recycler View Adapter

        return root;
    }


}
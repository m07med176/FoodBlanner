package iti.android.foodplanner.ui.features.plan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.room.Week;
import iti.android.foodplanner.databinding.FragmentPlanBinding;
import iti.android.foodplanner.ui.features.login.LoginActivity;

public class PlanFragment extends Fragment {
    private RecyclerView dayRecyclerView;
    private FragmentPlanBinding binding;
    private DaysAdapter daysAdapter;
    private PlanPresenter presenter;
    private List<Week> dayList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlanBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        presenter = new PlanPresenter(getContext());
        if (!presenter.isUser){

            getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
            Toast.makeText(getContext(), "Please Login First or Register", Toast.LENGTH_SHORT).show();
            return view;
        }
  


        dayList=new ArrayList<>();
        dayList.add(Week.SATURDAY);
        dayList.add(Week.SUNDAY);
        dayList.add(Week.MONDAY);
        dayList.add(Week.TUESDAY);
        dayList.add(Week.WEDNESDAY);
        dayList.add(Week.THURSDAY);
        dayList.add(Week.FRIDAY);



        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.daysRecycleView.setLayoutManager(linearLayoutManager);
        daysAdapter=new DaysAdapter(requireContext(),dayList);
        binding.daysRecycleView.setAdapter(daysAdapter);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (!presenter.isUser)
            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_home);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!presenter.isUser)
            Navigation.findNavController(binding.getRoot()).navigate(R.id.navigation_home);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
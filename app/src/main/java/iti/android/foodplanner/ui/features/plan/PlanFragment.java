package iti.android.foodplanner.ui.features.plan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.room.Week;
import iti.android.foodplanner.databinding.FragmentPlanBinding;

public class PlanFragment extends Fragment {
    private RecyclerView dayRecyclerView;
    private FragmentPlanBinding binding;
    private DaysAdapter daysAdapter;
    private List<Week> dayList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dayList=new ArrayList<>();
        dayList.add(Week.SATURDAY);
        dayList.add(Week.SUNDAY);
        dayList.add(Week.MONDAY);
        dayList.add(Week.TUESDAY);
        dayList.add(Week.WEDNESDAY);
        dayList.add(Week.THURSDAY);
        dayList.add(Week.FRIDAY);

        binding = FragmentPlanBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dayRecyclerView=root.findViewById(R.id.daysRecycleView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        dayRecyclerView.setLayoutManager(linearLayoutManager);
        daysAdapter=new DaysAdapter(requireContext(),dayList);
        dayRecyclerView.setAdapter(daysAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
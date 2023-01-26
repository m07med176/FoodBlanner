package iti.android.foodplanner.ui.features.onBoarding;

import android.nfc.cardemulation.OffHostApduService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import iti.android.foodplanner.R;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private List<SliderItem>sliderItems;
    private ViewPager2 viewPager2;

    SliderAdapter(List<SliderItem> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.slide_item_container,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setSliderItem(sliderItems.get(position));
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
    private ImageView imageView;
    private TextView titleTxtView;
    private TextView descTxtView;

      public SliderViewHolder(@NonNull View itemView) {
          super(itemView);
          imageView=itemView.findViewById(R.id.introImage);
          titleTxtView=itemView.findViewById(R.id.introHead);
          descTxtView=itemView.findViewById(R.id.introDesc);

      }

      public void setSliderItem(SliderItem sliderItem){
          imageView.setImageResource(sliderItem.getImage());
          titleTxtView.setText(sliderItem.getTitle());
          descTxtView.setText(sliderItem.getDescreption());
      }
  }

}

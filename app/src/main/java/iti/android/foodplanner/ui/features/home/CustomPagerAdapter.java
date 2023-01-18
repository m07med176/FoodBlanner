package iti.android.foodplanner.ui.features.home;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import iti.android.foodplanner.R;
import iti.android.foodplanner.data.models.meal.MealsItem;

public class CustomPagerAdapter  {
    class  SliderViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView roundedImageView;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.imgSlide);

        }
        public void setImage(MealsItem shortMeals){
            //roundedImageView.setImageResource(shortMeals.getStrMealThumb());
        }
    }

}

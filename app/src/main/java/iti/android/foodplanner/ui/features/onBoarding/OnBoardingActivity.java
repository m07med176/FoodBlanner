package iti.android.foodplanner.ui.features.onBoarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import iti.android.foodplanner.R;
import iti.android.foodplanner.ui.features.sign_in_with_google.SignUpOrLoginActivity;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    OnBoardingPresenter presenter;
    TabLayout tabLayout;
    Button nextBtn;

    @Override
    public void onStart() {
        super.onStart();
        if(presenter.isUser())
        {
            gotoApp();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        getSupportActionBar().hide();
        List<SliderItem> sliderItems=new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.img_intro,"Plan your weak plan meal \n" +
                "better with us ","choose any meal you want and added to your plan by just a click"));
        sliderItems.add(new SliderItem(R.drawable.img_intro,"Enjoy your lunch time","Just relax and not overthink what to eat. This is in our side with our personalized meal plans just prepared and adapted to your needs."));
        presenter = new OnBoardingPresenter(this);
        viewPager2=findViewById(R.id.viewPagerImageSlider);
        tabLayout=findViewById(R.id.tabLayoutIndicator);
        nextBtn=findViewById(R.id.nextButton);

        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));
        new TabLayoutMediator(tabLayout,viewPager2, (tab, position) -> tab.setText("")
        ).attach();
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingActivity.this, SignUpOrLoginActivity.class));
                finish();
            }
        });

//        viewPager2.setClipToPadding(false);
//        viewPager2.setClipChildren(false);
//        viewPager2.setOffscreenPageLimit(3);
//        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
//        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
//        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
//        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
//            @Override
//            public void transformPage(@NonNull View page, float position) {
//                float r=1-Math.abs(position);
//                page.setScaleY(.85f+r*.15f);
//
//            }
//        });
//        viewPager2.setPageTransformer(compositePageTransformer);
    }
    public void gotoApp() {
        startActivity(new Intent(OnBoardingActivity.this, SignUpOrLoginActivity.class));
       finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
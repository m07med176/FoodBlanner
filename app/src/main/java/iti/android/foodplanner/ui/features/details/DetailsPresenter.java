package iti.android.foodplanner.ui.features.details;

import android.content.Context;

public class DetailsPresenter {
    Context context;
    DetailsInterface detailsInterface;
    public DetailsPresenter(Context context,DetailsInterface detailsInterface) {
        this.detailsInterface = detailsInterface;
    }

    // TODO function Fetch data from ROOM or Network [ Details of food ]
    // TODO function Initialize Dialog of Add to Card
    // TODO function add to Favorite [ROOM]
}
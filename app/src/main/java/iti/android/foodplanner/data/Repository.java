package iti.android.foodplanner.data;

import android.content.Context;

import iti.android.foodplanner.data.network.ApiCalls;
import iti.android.foodplanner.data.network.Network;
import iti.android.foodplanner.data.room.RoomDatabase;
import iti.android.foodplanner.data.shared.SharedManager;

/**
 * Gather all functions from Network, Room Database And Shared Manager
 */
public class Repository {
    private Context context;
    private ApiCalls apiCalls;
    private RoomDatabase roomDatabase;
    private SharedManager sharedManager;

    public Repository(Context context) {
            apiCalls = Network.apiCalls;
            roomDatabase = RoomDatabase.getInstance(context);
            sharedManager = SharedManager.getInstance(context);
    }
}

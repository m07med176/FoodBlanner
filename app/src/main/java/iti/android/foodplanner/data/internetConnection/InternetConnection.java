package iti.android.foodplanner.data.internetConnection;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class InternetConnection extends LiveData<Boolean> {
    private ConnectivityManager connectivityManager;

    public InternetConnection(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private ConnectivityManager.NetworkCallback getNetworkCallback() {
        return new ConnectivityManager.NetworkCallback() {

            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                postValue(true);
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                postValue(false);
            }
        };
    }


    @Override
    protected void onInactive() {
        super.onInactive();
        try {
            connectivityManager.unregisterNetworkCallback(getNetworkCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActive() {
        super.onActive();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(getNetworkCallback());
        } else {
            connectivityManager.registerNetworkCallback(getNetworkRequest(), getNetworkCallback());
        }
    }


    private NetworkRequest getNetworkRequest() {
        return new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
                .addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                .build();
}

}
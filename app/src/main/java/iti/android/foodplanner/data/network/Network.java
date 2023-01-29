package iti.android.foodplanner.data.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private volatile static ApiCalls instance = null;

    private Network() {
    }

    public static synchronized ApiCalls getInstance(Context context) {
        if (instance == null) {
            Gson gson = new GsonBuilder().setLenient().create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiCalls.BASE_URL)
                    .client(cashAndLoggerManager(context))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            instance = retrofit.create(ApiCalls.class);
        }
        return instance;

    }


    public static OkHttpClient cashAndLoggerManager(Context context) {
        // Logging Retrofit
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // cash size
        Long cacheSize = Long.valueOf(20 * 1024 * 1024);
        Cache myCache = new Cache(context.getCacheDir(), cacheSize);

         return new OkHttpClient.Builder()
                .cache(myCache)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    if (hasNetwork(context))
                        request = request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build();
                    else
                        request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                    return chain.proceed(request);
                })
                 .addInterceptor(interceptor)
                 .build();
    }



    public static Boolean hasNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }




}

package iti.android.foodplanner.data.connectiviy;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import javax.net.SocketFactory;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

public class DoesNetworkHaveInternet {
    public Single<Boolean> single = Single.just(false);

    public Boolean execute(){
        Boolean retVal = false;
         try {
             Socket socket = new Socket();
            socket.connect(new InetSocketAddress("8.8.8.8",56), 1500);
            socket.close();
             retVal = true;
        } catch (IOException e) {
            retVal = false;
        }

        return retVal;
    }
}
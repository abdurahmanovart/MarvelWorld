package com.github.abdurahmanovart.marvelworld;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Abdurakhmanov on 18.09.17
 */

public class Utils {

    public Utils() {
        throw new IllegalStateException("can't create object");
    }

    /**
     * Method to check network accessibility
     *
     * @param context Context
     * @return true if there is connection, false otherwise
     */
    public static boolean hasConnection(Context context) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }
}
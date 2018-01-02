package mcssoft.com.raceremindermvp.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/** TBA - maybe use ConnectivityManager ?
 *        https://developer.android.com/reference/android/net/ConnectivityManager.html
 */
/**
 * Utility class to listen for network changes, i.e. at any one time do we have a connection.
 */
public class NetworkReceiver { //extends BroadcastReceiver {
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        ConnectivityManager connMgr = (ConnectivityManager)
//                context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//
//        if(networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
//            isConnected = true;
//        } else {
//            isConnected = false;
//        }
//    }
//
//    public boolean isConnected() {
//        return isConnected;
//    }
//
//    private boolean isConnected;
}

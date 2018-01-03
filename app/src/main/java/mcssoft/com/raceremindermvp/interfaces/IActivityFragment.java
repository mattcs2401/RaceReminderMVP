package mcssoft.com.raceremindermvp.interfaces;

import android.app.ProgressDialog;

import mcssoft.com.raceremindermvp.dialog.NetworkDialog;

/**
 * Interface between the Activity and the Fragment (so the fragment can call activity methods).
 */
public interface IActivityFragment {

    /**
     * Call on the Activity to show a dialog that no network connection exists.
     */
    void showNoNetworkDialog();

    /**
     * Call on the Activity to show a progress dialog.
     * @param show True - show dialog, else, False - dismiss dialog (if running)
     */
    void showProgressDialog(boolean show);
}

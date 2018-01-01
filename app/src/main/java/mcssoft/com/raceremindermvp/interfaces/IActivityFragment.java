package mcssoft.com.raceremindermvp.interfaces;

import android.app.ProgressDialog;

import mcssoft.com.raceremindermvp.dialog.NetworkDialog;

/**
 * Interface between the Activity and the Fragment (so the fragment can call activity methods).
 */
public interface IActivityFragment {

    void showNoNetworkDialog();

    NetworkDialog getNetworkDialog();

    void showProgressDialog(boolean show);

    ProgressDialog getProgressDialog();
}

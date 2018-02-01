package mcssoft.com.raceremindermvp.interfaces.fragment;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;

import mcssoft.com.raceremindermvp.dialog.NetworkDialog;

/**
 * Interface between the Activity and the Fragment (so the fragment can call activity methods).
 */
public interface IMainActivity {

    /**
     * Call on the Meeting activity to show a dialog that no network connection exists.
     */
    void showNoNetworkDialog();

    /**
     * Call on the Meeting activity to show a progress dialog.
     * @param show True - show dialog, else, False - dismiss dialog (if running)
     * @param message The message to display.
     */
    void showProgressDialog(boolean show, @Nullable String message);

    /**
     * Call on the Meeting activity to show a new Race activity.
     * @param lPos The position of the list item that was selected.
     */
    void showRaceFragment(int lPos);
}

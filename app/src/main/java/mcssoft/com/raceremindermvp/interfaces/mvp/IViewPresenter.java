package mcssoft.com.raceremindermvp.interfaces.mvp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import mcssoft.com.raceremindermvp.dialog.NetworkDialog;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;

/**
 * Implementation of interface between View and Presenter.
 */
public interface IViewPresenter {

    /**
     * Provide access to the application context.
     * @return The application context.
     */
    Context getContext();

    /**
     * Provide access to the Activity.
     * @return The Activity reference.
     */
    Activity getActivity();

    /**
     * Provide a reference to the RecyclerView (within the View).
     * @return The RecyclerView.
     */
    RecyclerView getRecyclerView();

    /**
     *
     * @return
     */
    IClick.ItemClick getClickListener();

    /**
     *
     * @return
     */
    void showProgressDialog(boolean show, @Nullable String message);

    /**
     *
     * @return
     */
    boolean isProgressDialogShowing();

    /**
     *
     * @return
     */
    void showNoNetworkDialog();
}

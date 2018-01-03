package mcssoft.com.raceremindermvp.interfaces.mvp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import mcssoft.com.raceremindermvp.dialog.NetworkDialog;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;

/**
 * Implementation of interface between Presenter and Model.
 */
public interface IPresenterModel {

    Context getContext();

    Activity getActivity();

    RecyclerView getRecyclerView();

    IClick.ItemClick getClickListener();

    void showProgressDialog(boolean show);

    void showNoNetworkDialog();
}

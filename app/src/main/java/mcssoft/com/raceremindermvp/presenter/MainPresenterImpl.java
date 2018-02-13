package mcssoft.com.raceremindermvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.lang.ref.WeakReference;

import mcssoft.com.raceremindermvp.interfaces.click.IClick;
import mcssoft.com.raceremindermvp.interfaces.mvp.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterView;
import mcssoft.com.raceremindermvp.interfaces.mvp.IViewPresenter;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.impl.MeetingModelImpl;
import mcssoft.com.raceremindermvp.model.impl.RaceModelImpl;

public class MainPresenterImpl implements IPresenterModel, IPresenterView {

    /**
     * Implemeentation of the main Presenter.
     * @param iViewPresenter reference to IViewPresenter interface.
     * @param arguments Optional arguments passed to Model.
     */
    public MainPresenterImpl(IViewPresenter iViewPresenter, @Nullable Bundle arguments) {
        this.iViewPresenter = iViewPresenter;
        if(arguments == null) {
            iModelPresenter = new MeetingModelImpl(this);
        } else {
            iModelPresenter = new RaceModelImpl(this, arguments);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterModel">
    @Override
    public Context getContext() { return iViewPresenter.getContext(); }

    @Override
    public Activity getActivity() {
        return iViewPresenter.getActivity();
    }

    @Override
    public RecyclerView getRecyclerView() {
        return iViewPresenter.getRecyclerView();
    }

    @Override
    public IClick.ItemClick getClickListener() {
        return iViewPresenter.getClickListener();
    }

    @Override
    public void showProgressDialog(boolean show, String message) {
        iViewPresenter.showProgressDialog(show, message);
    }

    @Override
    public boolean isProgressDialogShowing() {
        return iViewPresenter.isProgressDialogShowing();
    }

    @Override
    public void showNoNetworkDialog() {
        iViewPresenter.showNoNetworkDialog();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterView">
    @Override
    public void deleteMeetings() {
        iModelPresenter.deleteMeetings();
    }

    @Override
    public void downloadMeetings() {
        iModelPresenter.downloadMeetings();
    }

    @Override
    public void clearDisplay() {
        iModelPresenter.clearDisplay();
    }

    @Override
    public int getMeetingRowId(int lPos) { return iModelPresenter.getMeetingRowId(lPos); }
    //</editor-fold>

    private WeakReference<IViewPresenter> iPresenterView;  // IPresenterView reference
    private IModelPresenter iModelPresenter;
    private IViewPresenter iViewPresenter;
}

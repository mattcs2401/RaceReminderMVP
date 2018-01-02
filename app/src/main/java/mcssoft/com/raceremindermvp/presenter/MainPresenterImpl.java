package mcssoft.com.raceremindermvp.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.lang.ref.WeakReference;

import mcssoft.com.raceremindermvp.dialog.NetworkDialog;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;
import mcssoft.com.raceremindermvp.interfaces.mvp.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterView;
import mcssoft.com.raceremindermvp.interfaces.mvp.IViewPresenter;
import mcssoft.com.raceremindermvp.model.impl.MainModelImpl;

public class MainPresenterImpl implements IPresenterModel, IPresenterView {

    public MainPresenterImpl(IViewPresenter iViewPresenter) {
        this.iViewPresenter = iViewPresenter;
        iModelPresenter = new MainModelImpl(this);
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
    public ProgressDialog getProgressDialog() {
        return iViewPresenter.getProgressDialog();
    }

    @Override
    public NetworkDialog getNetworkDialog() {
        return iViewPresenter.getNetworkDialog();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterView">
    @Override
    public boolean getNetworkCheck() {
        return iModelPresenter.getNetworkCheck();
    }

    @Override
    public int getMeetings() {
        return iModelPresenter.getMeetings();
    }

    @Override
    public int getRaces() {
        return iModelPresenter.getRaces();
    }
    //</editor-fold>


    private WeakReference<IViewPresenter> iPresenterView;  // IPresenterView reference
    private IModelPresenter iModelPresenter;
    private IViewPresenter iViewPresenter;
}

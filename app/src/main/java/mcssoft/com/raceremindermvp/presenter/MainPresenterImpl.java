package mcssoft.com.raceremindermvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
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
    public void showProgressDialog(boolean show, String message) {
        iViewPresenter.showProgressDialog(show, message);
    }

    @Override
    public void showNoNetworkDialog() {
        iViewPresenter.showNoNetworkDialog();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterView">
    @Override
    public boolean getNetworkCheck() {
        return iModelPresenter.getNetworkCheck();
    }

    @Override
    public void getMeetings() {
        iModelPresenter.getMeetings();
    }

    @Override
    public void getRaces() { iModelPresenter.getRaces(); }
    //</editor-fold>


    private WeakReference<IViewPresenter> iPresenterView;  // IPresenterView reference
    private IModelPresenter iModelPresenter;
    private IViewPresenter iViewPresenter;
}

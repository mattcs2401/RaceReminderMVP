package mcssoft.com.raceremindermvp.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;

import mcssoft.com.raceremindermvp.interfaces.IPresenterModel;
import mcssoft.com.raceremindermvp.interfaces.IPresenterView;
import mcssoft.com.raceremindermvp.interfaces.IViewPresenter;

public class RacePresenter implements IPresenterModel, IPresenterView {

    public RacePresenter(IViewPresenter view) {
        presenterView = new WeakReference<IViewPresenter>(view);
        String bp="";
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterModel">
    @Override
    public Context getContext() {
        return null;
    }
    //</editor-fold>

    private WeakReference<IViewPresenter> presenterView;
}

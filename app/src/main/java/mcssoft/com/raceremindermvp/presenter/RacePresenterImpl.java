package mcssoft.com.raceremindermvp.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;

import mcssoft.com.raceremindermvp.interfaces.IPresenterModel;
import mcssoft.com.raceremindermvp.interfaces.IPresenterView;
import mcssoft.com.raceremindermvp.interfaces.IViewPresenter;
import mcssoft.com.raceremindermvp.model.impl.RaceModelImpl;

public class RacePresenterImpl implements IPresenterModel, IPresenterView {

    public RacePresenterImpl(IViewPresenter view) {
        viewPresenter = new WeakReference<IViewPresenter>(view);
        raceModel = new RaceModelImpl(model);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterModel">
    /**
     * Get the Context from the view.
     * @return The view's context.
     */
    @Override
    public Context getContext() {
        return viewPresenter.get().getContext();
    }
    //</editor-fold>

    private WeakReference<IViewPresenter> viewPresenter;     // view reference
    private IPresenterModel model;
    private RaceModelImpl raceModel;
}

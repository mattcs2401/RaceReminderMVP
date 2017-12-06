package mcssoft.com.raceremindermvp.interfaces;

import android.content.Context;

/**
 * Implementation of interface between View and Presenter.
 * Provides View operations available to the Presenter.
 */
public interface IViewPresenter {

    public Context getContext();

    public IPresenterView getPresenter(IPresenterView iPresenterView);

}

package mcssoft.com.raceremindermvp.interfaces;

import android.content.Context;

/**
 * Implementation of interface between Presenter and Model.
 * Provides Presenter operations available to the Model.
 */
public interface IPresenterModel {

    public Context getContext();

    public IModelPresenter getModel(IModelPresenter model);

}

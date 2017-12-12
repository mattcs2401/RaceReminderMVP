package mcssoft.com.raceremindermvp.interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Implementation of interface between Model and Presenter.
 * Provides Model operations available to the Presenter.
 */
public interface IModelPresenter {

    RecyclerView getRecyclerView();

}

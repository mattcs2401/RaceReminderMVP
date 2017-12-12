package mcssoft.com.raceremindermvp.interfaces;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Implementation of interface between Presenter and Model.
 * Provides Presenter operations available to the Model.
 */
public interface IPresenterModel {

    Context getContext();

    Activity getActivity();

    RecyclerView getRecyclerView();

    IClick.ItemClick getClickListener();

    IModelPresenter getModel(IModelPresenter model);

}

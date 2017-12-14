package mcssoft.com.raceremindermvp.interfaces.mvp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import mcssoft.com.raceremindermvp.interfaces.click.IClick;

/**
 * Implementation of interface between View and Presenter.
 * Provides View operations available to the Presenter.
 */
public interface IViewPresenter {

    /**
     * Provide access to the application context.
     * @return The application context.
     */
    Context getContext();

    /**
     * Provide access to the Activity.
     * @return The Activity reference.
     */
    Activity getActivity();

    /**
     * Provide a reference to the RecyclerView (within the View).
     * @return The RecyclerView.
     */
    RecyclerView getRecyclerView();

    /**
     *
     * @return
     */
    IClick.ItemClick getClickListener();
    /**
     * Provide access to the methods of the IPresenterView interface.
     * @param iPresenterView The IPresenterView reference.
     * @return 'this' from the PresenterImpl class.
     */
    IPresenterView getPresenter(IPresenterView iPresenterView);

}

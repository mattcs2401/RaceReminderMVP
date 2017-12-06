package mcssoft.com.raceremindermvp.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;

import mcssoft.com.raceremindermvp.interfaces.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.IPresenterModel;
import mcssoft.com.raceremindermvp.interfaces.IPresenterView;
import mcssoft.com.raceremindermvp.interfaces.IViewPresenter;
import mcssoft.com.raceremindermvp.model.impl.RaceModelImpl;

public class RacePresenterImpl implements IPresenterModel, IPresenterView {

    public RacePresenterImpl() {
    }

    public RacePresenterImpl(IViewPresenter view) {
        setView(view);
        raceModelImpl = new RaceModelImpl(this);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterModel">
    /**
     * Get the Context from the view.
     * @return The view's context.
     */
    @Override
    public Context getContext() {
        return getView().getContext();
    }

    /**
     * Provide a reference to IModelPresenter interface to access methods implemented by the Model.
     * @param iModelPresenter The IModelPresenter interface.
     * @return A reference to the IModelPresenter interface.
     */
    @Override
    public IModelPresenter getModel(IModelPresenter iModelPresenter) {
        // set the reference to the IModelPresenter interface.
        this.iModelPresenter = iModelPresenter;
        return iModelPresenter;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterView">
    // TBA
    //</editor-fold>

    /**
     * Get the View associated with this Presenter.
     * @return The associated View.
     * @throws NullPointerException
     */
    public IViewPresenter getView() throws NullPointerException {
        // TODO - can we do better than throwing an exception ?
        if(view != null) {
            return view.get();
        } else {
            throw new NullPointerException("Unable to get View.");
        }
    }

    /**
     * Set the View associated with this Presenter.
     * @param view The associated View.
     */
    public void setView(IViewPresenter view) {
        this.view = new WeakReference<IViewPresenter>(view);
    }

    private WeakReference<IViewPresenter> view;     // view reference
    private IPresenterModel iPresenterModel;
    private IModelPresenter iModelPresenter;
    private RaceModelImpl raceModelImpl;
}

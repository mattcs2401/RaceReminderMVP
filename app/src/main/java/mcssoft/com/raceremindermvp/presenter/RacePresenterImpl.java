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
        setiPresenterView(view);
        raceModelImpl = new RaceModelImpl(this);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterModel">
    /**
     * Get the Context from the iPresenterView.
     * @return The iPresenterView's context.
     */
    @Override
    public Context getContext() {
        return getiPresenterView().getContext();
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
     * Get the IPresenterView interface.
     * @return The IPresenterView interface.
     * Note: This is for the View's access to the Presenter's methods exposed with the
     *       IPresenterView interface.
     */
    public IPresenterView getPresenter() {
        return iPresenterView.get().getPresenter(this);
    }

    /**
     * Get the View associated with this Presenter.
     * @return The associated View.
     * @throws NullPointerException
     */
    public IViewPresenter getiPresenterView() throws NullPointerException {
        // TODO - can we do better than throwing an exception ?
        if(iPresenterView != null) {
            return iPresenterView.get();
        } else {
            throw new NullPointerException("Unable to get View.");
        }
    }

    /**
     * Set the View associated with this Presenter.
     * @param iPresenterView The associated View.
     */
    public void setiPresenterView(IViewPresenter iPresenterView) {
        this.iPresenterView = new WeakReference<IViewPresenter>(iPresenterView);
    }

    private WeakReference<IViewPresenter> iPresenterView;  // IPresenterView reference
    private IPresenterModel iPresenterModel;
    private IModelPresenter iModelPresenter;
    private RaceModelImpl raceModelImpl;
}

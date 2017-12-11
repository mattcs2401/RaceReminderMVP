package mcssoft.com.raceremindermvp.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.List;

import mcssoft.com.raceremindermvp.interfaces.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.IPresenterModel;
import mcssoft.com.raceremindermvp.interfaces.IPresenterView;
import mcssoft.com.raceremindermvp.interfaces.IViewPresenter;
import mcssoft.com.raceremindermvp.model.Race;
import mcssoft.com.raceremindermvp.model.impl.RaceModelImpl;

public class RacePresenterImpl implements IPresenterModel, IPresenterView {

    public RacePresenterImpl() {
    }

    public RacePresenterImpl(IViewPresenter iViewPresenter) {
        setIPresenterView(iViewPresenter);
        raceModelImpl = new RaceModelImpl(this);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterModel">
    /**
     * Get the Context from the IPresenterView.
     * @return The iPresenterView's context.
     */
    @Override
    public Context getContext() {
        return getIPresenterView().getContext();
    }

    @Override
    public RecyclerView getRecyclerView() {
        return iPresenterView.get().getRecyclerView();
//        return iPresenterModel.getRecyclerView();
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
    @Override
    public Race getRace(int id) {
        return iModelPresenter.getRace(id);
    }

    @Override
    public List<Race> getRaces(String whereCondition) {
        return iModelPresenter.getRaces(whereCondition);
    }
    
    @Override
    public List<Race> getRaces() {
        return iModelPresenter.getRaces();
    }
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
    public IViewPresenter getIPresenterView() throws NullPointerException {
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
    public void setIPresenterView(IViewPresenter iPresenterView) {
        this.iPresenterView = new WeakReference<IViewPresenter>(iPresenterView);
    }

    private WeakReference<IViewPresenter> iPresenterView;  // IPresenterView reference
    private IPresenterModel iPresenterModel;
    private IModelPresenter iModelPresenter;
    private RaceModelImpl raceModelImpl;
}

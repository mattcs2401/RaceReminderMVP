package mcssoft.com.raceremindermvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

import mcssoft.com.raceremindermvp.interfaces.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.IPresenterModel;
import mcssoft.com.raceremindermvp.interfaces.IPresenterView;
import mcssoft.com.raceremindermvp.interfaces.IViewPresenter;
import mcssoft.com.raceremindermvp.model.Race;
import mcssoft.com.raceremindermvp.model.impl.RaceModelImpl;

public class RacePresenterImpl implements IPresenterModel, IPresenterView {

    public RacePresenterImpl(IViewPresenter iViewPresenter) {
        this.iViewPresenter = iViewPresenter;
        raceModelImpl = new RaceModelImpl(this);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterModel">
    @Override
    public Context getContext() {
        return iViewPresenter.getContext();
    }

    @Override
    public Activity getActivity() {
        return iViewPresenter.getActivity();
    }

    @Override
    public RecyclerView getRecyclerView() {
        return iViewPresenter.getRecyclerView();
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


    private WeakReference<IViewPresenter> iPresenterView;  // IPresenterView reference
    private IPresenterModel iPresenterModel;
    private IModelPresenter iModelPresenter;
    private RaceModelImpl raceModelImpl;
    private IViewPresenter iViewPresenter;
}

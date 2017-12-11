package mcssoft.com.raceremindermvp.model.impl;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.adapter.RaceAdapter;
import mcssoft.com.raceremindermvp.interfaces.IClick;
import mcssoft.com.raceremindermvp.interfaces.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.IPresenterModel;
import mcssoft.com.raceremindermvp.loader.RaceTaskLoader;
import mcssoft.com.raceremindermvp.model.Race;
import mcssoft.com.raceremindermvp.model.RaceList;

public class RaceModelImpl implements IModelPresenter, IClick.ItemClick { //}, LoaderManager.LoaderCallbacks<List<Race>> {

    public RaceModelImpl(IPresenterModel iPresenterModel) {
        this.iPresenterModel = iPresenterModel;
        raceList = new RaceList();
        setRaceAdapter();
        setRecyclerView();
    }

    /**
     * Get the IModelPresenter interface.
     * @return The IModelPresenter interface.
     * Note: This is for the Presenter's access to the Model's methods exposed with the
     *       IModelPresenter interface.
     */
    public IModelPresenter getModel() {
        return iPresenterModel.getModel(this);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter">
    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public Race getRace(int id) {
        return null;
    }

    @Override
    public List<Race> getRaces(String whereCondition) {
        return null;
    }

    @Override
    public List<Race> getRaces() {
        raceLoader = new RaceTaskLoader(iPresenterModel.getContext());
        raceLoader.loadInBackground();
        return raceList.getRaces();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IClick.ItemClick">
    @Override
    public void onItemClick(View view, @Nullable int lPos) {
        // Actual TBA
        Snackbar.make(view, "Item " + lPos + " selected.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    //</editor-fold>

    public void setRaceAdapter() {
        raceAdapter = new RaceAdapter();
        raceAdapter.setOnItemClickListener(this);
    }

    private void setRecyclerView() {
        recyclerView = iPresenterModel.getRecyclerView();
        recyclerView.setAdapter(raceAdapter);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Loader callbacks">
//    @Override
//    public Loader<List<Race>> onCreateLoader(int id, Bundle args) {
//        return new RaceTaskLoader(iPresenterModel.getContext());
//    }
//
//    @Override
//    public void onLoadFinished(Loader<List<Race>> loader, List<Race> data) {
//        if(data != null && data.size() > 0) {
//            raceList.setRaces(data);
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<List<Race>> loader) {
//        // TBA - recyclerview adapter reset ?
//    }
    //</editor-fold>

    private RaceList raceList;
    private RaceAdapter raceAdapter;//
    private RecyclerView recyclerView;
    private RaceTaskLoader raceLoader;     // access to database.
    private IPresenterModel iPresenterModel; // access to Presenter.
}

package mcssoft.com.raceremindermvp.loader;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.ILoaderRace;
import mcssoft.com.raceremindermvp.interfaces.IModelLoader;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;

public class RaceLoaderManager implements LoaderManager.LoaderCallbacks, ILoaderRace {

    public RaceLoaderManager(IPresenterModel iPresenterModel, IModelLoader iModelLoader) {
        this.iPresenterModel = iPresenterModel;
        this.iModelLoader = iModelLoader;
    }

    public void initLoader(RaceDatabase raceDatabase, Bundle args) {
        this.args = args;                   // local copy so we can (update)/pass back to Model.
        this.raceDatabase = raceDatabase;   // local copy, passed to RaceLoader.
        iPresenterModel.getActivity().getLoaderManager().initLoader(0, args, this);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: LoaderCallbacks">
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new RaceLoader(iPresenterModel.getContext(), raceDatabase, args, this);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        iModelLoader.onFinished(loader, data, args);
    }

    @Override
    public void onLoaderReset(Loader loader) { }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: ILoaderRace">
    @Override
    public void deliverResult(Object data) {

        String bp = "";
    }
    //</editor-fold>

    // TBA
    public enum LoaderTasks {
        CHECK_FOR_MEETINGS, INSERT_MEETING, INSERT_MEETINGS, UPDATE_MEETING
    }

    private Bundle args;
    private RaceDatabase raceDatabase;
    private IPresenterModel iPresenterModel;
    private IModelLoader iModelLoader;
}

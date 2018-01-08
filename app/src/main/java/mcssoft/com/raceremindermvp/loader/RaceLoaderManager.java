package mcssoft.com.raceremindermvp.loader;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import mcssoft.com.raceremindermvp.interfaces.IModelLoader;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;

public class RaceLoaderManager implements LoaderManager.LoaderCallbacks {

    public RaceLoaderManager(IPresenterModel iPresenterModel, IModelLoader iModelLoader) {
        this.iPresenterModel = iPresenterModel;
        this.iModelLoader = iModelLoader;
    }

    public void initLoader(Bundle args) {
        this.args = args; // save local so we can (update)/pass back to Model.
        iPresenterModel.getActivity().getLoaderManager().initLoader(0, args, this);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: LoaderCallbacks">
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new RaceLoader(iPresenterModel.getContext(), args);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        iModelLoader.onFinished(loader, data, args);
    }

    @Override
    public void onLoaderReset(Loader loader) { }
    //</editor-fold>

    public enum LoaderTasks {
        CHECK_FOR_MEETINGS, INSERT_MEETING, INSERT_MEETINGS, UPDATE_MEETING
    }

    private Bundle args;
    private boolean meetingsExist;
    private IPresenterModel iPresenterModel;
    private IModelLoader iModelLoader;
}

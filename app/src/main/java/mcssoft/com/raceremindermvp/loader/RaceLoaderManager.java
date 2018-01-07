package mcssoft.com.raceremindermvp.loader;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;

public class RaceLoaderManager implements LoaderManager.LoaderCallbacks {

    public RaceLoaderManager(IPresenterModel iPresenterModel) {
        this.iPresenterModel = iPresenterModel;
    }

    public boolean checkMeetingsExistInDatabase(RaceDatabase raceDatabase) {
        Bundle bundle = new Bundle();
        iPresenterModel.getActivity().getLoaderManager().initLoader(0, bundle, this);
        return false;
    }

    //<editor-fold defaultstate="collapsed" desc="Region: LoaderCallbacks">
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new RaceLoader(iPresenterModel.getContext());
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
    //</editor-fold>

    private IPresenterModel iPresenterModel;
}

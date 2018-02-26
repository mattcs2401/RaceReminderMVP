package mcssoft.com.raceremindermvp.model.impl.base;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.mvp.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.database.Race;

/**
 * Utility base class for the implementation of the Model.
 */
public abstract class BaseModelImpl
        implements Response.Listener, Response.ErrorListener, LoaderManager.LoaderCallbacks, IModelPresenter.IMeeting, IModelPresenter.IRace {

    //<editor-fold defaultstate="collapsed" desc="Region: Volley">
    @Override
    public void onErrorResponse(VolleyError error) { }

    @Override
    public void onResponse(Object response) { }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Loader">
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) { }

    @Override
    public void onLoaderReset(Loader loader) { }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter.IMeeting">
    @Override
    public void deleteMeetings() { }

    @Override
    public void downloadMeetings() { }

    @Override
    public void clearMeetingDisplay() { }

    @Override
    public Meeting getMeeting(int lPos) { return null; }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter.IRace">
    @Override
    public void deleteRaces() { }

    @Override
    public void downloadRaces() { }

    @Override
    public void clearRaceDisplay() { }

    @Override
    public Race getRace(int lPos) { return null; }

    @Override
    public List<String> getMeetingInfo() { return null; }
    //</editor-fold>

    protected void setAdapter() { }

    /**
     * Set the applicable loader manager (initialise if loader is null, else restart).
     * @param id The id of the loader.
     * @param bundle A data bundle that's passed to the loader.
     */
    protected void setLoaderManager(@NonNull int id, @NonNull Bundle bundle) {
        if(loaderManager.getLoader(id) != null) {
            // restart the loader to pick up new changes.
            loaderManager.restartLoader(id, bundle, this);
        } else {
            loaderManager.initLoader(id, bundle, this);
        }
    }

    /**
     * Check that a connection exists.
     * @return True if a connection exists.
     */
    protected boolean getNetworkCheck() {
        boolean networkExists = true;
        ConnectivityManager connMgr = (ConnectivityManager) iPresenterModel.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null || (!networkInfo.isAvailable() && !networkInfo.isConnected())) {
            networkExists = false;
        }
        return networkExists;
    }

    protected RaceDatabase raceDatabase;
    protected LoaderManager loaderManager;
    protected IPresenterModel iPresenterModel;
}

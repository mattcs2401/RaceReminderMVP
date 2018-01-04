package mcssoft.com.raceremindermvp.loader;

import java.util.List;
import android.app.LoaderManager;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.utility.Resources;

/**
 * Utility class to manage the Insert and Select loaders.
 */
public class LoadersManager implements LoaderManager.LoaderCallbacks {

    public LoadersManager(IPresenterModel iPresenterModel) {
        this.context = iPresenterModel.getContext();

        // create database instance.
        raceDatabase = Room.databaseBuilder(context, RaceDatabase.class, "RaceDatabase.db").build();
        // initialise the Loader.
//        iPresenterModel.getActivity().getLoaderManager().initLoader(0, null, this);

    }

    /**
     * Insert in the database.
     * @param object The Volley response object.
     * @param which Which database table.
     * @return a TBA value.
     */
    public int insert(Object object, String which) {
        this.object = object;
        Bundle bundle = new Bundle();
        switch (which) {
            case "MEETINGS":
                bundle.putInt("key", Resources.getInstance(context).getInteger(R.integer.insert_for_meetings));
                iPresenterModel.getActivity().getLoaderManager().initLoader(INSERT_LOADER, bundle, this);
                break;
            case "RACES":
                break;
        }
        return 0;
    }

    //<editor-fold defaultstate="collapsed" desc="Region: LoaderCallbacks">
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        switch(id) {
            case INSERT_LOADER:
                return new InsertLoader(context, raceDatabase, object, args);
            case SELECT_LOADER:
                //return new SelectLoader(context, raceDatabase, object);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
    //</editor-fold>

    private int insertMeetings(Object object) {

        return 0;
    }

    private Object object;
    private Context context;
    private RaceDatabase raceDatabase;
    private IPresenterModel iPresenterModel;

    private static final int INSERT_LOADER = 0x01;
    private static final int SELECT_LOADER = 0x02;
}
/*
    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        return new MeetingLoader(iPresenterModel.getContext(), raceDatabase);
    }

    @Override
    public void onLoadFinished(Loader<List> loader, List list) {
        meetingAdapter.swapData(list);
        if(list != null && list.size() > 0) {
            meetingAdapter.setEmptyView(false);
        } else {
            meetingAdapter.setEmptyView(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<List> loader) {
        meetingAdapter.swapData(null);
    }
*/

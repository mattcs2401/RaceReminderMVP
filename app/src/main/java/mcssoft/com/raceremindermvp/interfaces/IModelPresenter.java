package mcssoft.com.raceremindermvp.interfaces;

import java.util.List;

import mcssoft.com.raceremindermvp.model.Race;

/**
 * Implementation of interface between Model and Presenter.
 * Provides Model operations available to the Presenter.
 */
public interface IModelPresenter {

    /**
     * Get a List of Races from the Model.
     * @return A list of Races.
     */
    public List<Race> getRaces();
}

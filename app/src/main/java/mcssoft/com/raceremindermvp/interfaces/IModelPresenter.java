package mcssoft.com.raceremindermvp.interfaces;

import java.util.List;

import mcssoft.com.raceremindermvp.model.Race;

/**
 * Implementation of interface between Model and Presenter.
 * Provides Model operations available to the Presenter.
 */
public interface IModelPresenter {

    /**
     * Get a Race from the Model.
     * @param id The Race identifier.
     * @return The Race object.
     */
    public Race getRace(int id);

    /**
     * Get a List of Races from the Model.
     * @return A list of Races.
     */
    public List<Race> getRaces();

    /**
     * Get a List of Races from the Model.
     * @param whereCondition A where clause for database query.
     * @return
     */
    public List<Race> getRaces(String whereCondition);
}

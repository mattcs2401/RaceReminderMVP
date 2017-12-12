package mcssoft.com.raceremindermvp.interfaces;

import java.util.List;

import mcssoft.com.raceremindermvp.database.Race;

/**
 * Implementation of interface between Presenter and View.
 * Provides Presenter operations available to the View.
 */
public interface IPresenterView {

    /**
     * Get a Race from the Presenter.
     * @param id The Race identifier.
     * @return The Race object.
     */
    public Race getRace(int id);

    /**
     * Get a List of Races from the Presenter.
     * @return A list of Races.
     */
    public List<Race> getRaces();

    /**
     * Get a List of Races from the Presenter.
     * @param whereCondition A where clause for database query.
     * @return
     */
    public List<Race> getRaces(String whereCondition);
}

package mcssoft.com.raceremindermvp.interfaces;

import java.util.List;

import mcssoft.com.raceremindermvp.model.Race;

public interface IModelPresenter {

    /**
     * Get a List of Races from the Model.
     * @return A list of Races.
     */
    public List<Race> getRaces();
}

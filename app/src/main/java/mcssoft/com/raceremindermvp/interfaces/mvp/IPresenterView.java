package mcssoft.com.raceremindermvp.interfaces.mvp;

/**
 * Implementation of interface between Presenter and View.
 * Provides Presenter operations available to the View.
 */
public interface IPresenterView {

    /**
     * Delete all Meeting objects from the database (and display).
     */
    void deleteMeetings();

}

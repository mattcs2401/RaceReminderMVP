package mcssoft.com.raceremindermvp.interfaces.mvp;

/**
 * Implementation of interface between Model and Presenter.
 * Provides Model operations available to the Presenter.
 */
public interface IModelPresenter {

    /**
     * Check a network connection exists.
     * @return
     */
    boolean getNetworkCheck();

    /**
     * Get the Meetings for today.
     */
    void getMeetings();

    /**
     * Get the Races for a meeting.
     */
    void getRaces();
}

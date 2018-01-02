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
     * @return A count of the Meetings.
     */
    int getMeetings();

    /**
     * Get the Races for a meeting.
     * @return A count of the Races.
     */
    int getRaces();
}

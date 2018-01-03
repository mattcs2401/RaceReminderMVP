package mcssoft.com.raceremindermvp.interfaces.mvp;

/**
 * Implementation of interface between Presenter and View.
 * Provides Presenter operations available to the View.
 */
public interface IPresenterView {

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

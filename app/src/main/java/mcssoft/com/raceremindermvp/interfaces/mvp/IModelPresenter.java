package mcssoft.com.raceremindermvp.interfaces.mvp;

/**
 * Implementation of interface between Model and Presenter.
 * Provides Model operations available to the Presenter.
 */
public interface IModelPresenter {

    void deleteMeetings();

    /**
     * Check a network connection exists.
     * @return
     */
//    boolean getNetworkCheck();

    /**
     * Download the Meetings for today.
     */
//    void downloadMeetings();

    /**
     * Get the Meetings  from the database.
     * @return A list of the Meetings
     */
//    Object getMeetings();

    /**
     * Download the Races for a meeting.
     */
//    void downloadRaces();

    /**
     * Get the Races  from the database.
     * @return A list of the Races
     */
//    Object getRaces();
}

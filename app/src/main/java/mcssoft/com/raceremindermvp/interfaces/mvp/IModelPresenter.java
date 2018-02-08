package mcssoft.com.raceremindermvp.interfaces.mvp;

import mcssoft.com.raceremindermvp.model.database.Meeting;

/**
 * Implementation of interface between Model and Presenter.
 * Provides Model operations available to the Presenter.
 */
public interface IModelPresenter {

    /**
     * Delete all Meeting objects from the database (and display).
     */
    void deleteMeetings();

    void downloadMeetings();

    void clearDisplay();

    Meeting getMeeting(int lPos);
}

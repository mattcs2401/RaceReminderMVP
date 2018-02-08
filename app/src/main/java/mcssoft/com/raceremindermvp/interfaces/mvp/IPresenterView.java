package mcssoft.com.raceremindermvp.interfaces.mvp;

import mcssoft.com.raceremindermvp.model.database.Meeting;

/**
 * Implementation of interface between Presenter and View.
 * Provides Presenter operations available to the View.
 */
public interface IPresenterView {

    /**
     * Delete all Meeting objects from the database (and display).
     */
    void deleteMeetings();

    void downloadMeetings();

    void clearDisplay();

    int getMeetingRowId(int lPos);
}

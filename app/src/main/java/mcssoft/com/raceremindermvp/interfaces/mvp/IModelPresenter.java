package mcssoft.com.raceremindermvp.interfaces.mvp;

import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.database.Race;

/**
 * Implementation of interface between Model and Presenter.
 * Provides Model operations available to the Presenter.
 */
public interface IModelPresenter {

    interface IMeeting {
        /**
         * Delete all Meeting objects from the database (and display).
         */
        void deleteMeetings();

        /**
         * Download Meeting information from Tatts.
         */
        void downloadMeetings();

        /**
         * Clear the display adapter.
         */
        void clearMeetingDisplay();

        /**
         * Get the Meeting for the display adapter position.
         * @param lPos The adapter position
         * @return The Meeting object.
         */
        Meeting getMeeting(int lPos);
    }

    interface IRace {
        /**
         * Delete all Race objects from the database (and display).
         */
        void deleteRaces();

        /**
         * Download Race information from Tatts.
         */
        void downloadRaces();

        /**
         * Clear the display adapter.
         */
        void clearRaceDisplay();

        /**
         * Get the Race for the display adapter position.
         * @param lPos The adapter position
         * @return The Race object.
         */
        Race getRace(int lPos);
    }
}

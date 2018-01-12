package mcssoft.com.raceremindermvp.database;

public class DatabaseProjection {

    /**
     * Return the projection (column lis) associated with the parameter.
     * @param projection The table's schema name.
     * @return The projection for the table.
     */
    public static final String [] getProjection(DatabaseHelper.Projection projection) {
        switch (projection) {
            case MeetingSchema:
                return getMeetingsProjection();
            case RaceSchema:
                return getRacesProjection();
            case RunnerSchema:
                return getRunnersProjection();
        }
        return  null;
    }

    private static final String[] getMeetingsProjection() {
        return new String[] {
                DatabaseConstants.MEETING_ROWID,
                DatabaseConstants.MEETING_ABANDONED,
                DatabaseConstants.MEETING_VENUE,
                DatabaseConstants.MEETING_HI_RACE,
                DatabaseConstants.MEETING_CODE,
                DatabaseConstants.MEETING_ID,
                DatabaseConstants.MEETING_DATE,
                DatabaseConstants.MEETING_TRACK_DESC,
                DatabaseConstants.MEETING_TRACK_RATING,
                DatabaseConstants.MEETING_WEATHER_DESC};
    }

    private static final String[] getRacesProjection() {
        return new String[] {
                DatabaseConstants.RACE_ROWID,
                DatabaseConstants.RACE_MEETING_ID,
                DatabaseConstants.RACE_NO,
                DatabaseConstants.RACE_TIME,
                DatabaseConstants.RACE_NAME,
                DatabaseConstants.RACE_DIST};
    }

    private static final String[] getRunnersProjection() {
        return new String[] {
                DatabaseConstants.RUNNER_ROWID,
                DatabaseConstants.RUNNER_MEETING_ID,
                DatabaseConstants.RUNNER_RACE_NO,
                DatabaseConstants.RUNNER_NO,
                DatabaseConstants.RUNNER_NAME,
                DatabaseConstants.RUNNER_SCR,
                DatabaseConstants.RUNNER_JOCKEY,
                DatabaseConstants.RUNNER_BARRIER,
                DatabaseConstants.RUNNER_HCAP,
                DatabaseConstants.RUNNER_WEIGHT,
                DatabaseConstants.RUNNER_FORM,
                DatabaseConstants.RUNNER_LRES,
                DatabaseConstants.RUNNER_RATING
        };
    }

}

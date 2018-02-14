package mcssoft.com.raceremindermvp.utility;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Utility class to define types of operations to be performed.
 */
public final class OpType {

    @IntDef ({MType.DOWNLOAD_MEETINGS, MType.COUNT_MEETINGS, MType.SELECT_MEETINGS,
            MType.INSERT_MEETINGS, MType.DELETE_MEETING, MType.DELETE_MEETINGS})

    @Retention(RetentionPolicy.SOURCE)

    public @interface MType {
        int DOWNLOAD_MEETINGS = 0x00;
        int COUNT_MEETINGS = 0x01;
        int SELECT_MEETINGS = 0x02;
        int INSERT_MEETINGS = 0x03;
        int DELETE_MEETING = 0x04;
        int DELETE_MEETINGS = 0x05;
    }

    @IntDef({RType.DOWNLOAD_RACES, RType.COUNT_RACES, RType.SELECT_RACES, RType.INSERT_RACES,
            RType.DELETE_RACE, RType.DELETE_RACES})

    @Retention(RetentionPolicy.SOURCE)

    public @interface RType {
        int DOWNLOAD_RACES = 0x10;
        int COUNT_RACES = 0x11;
        int SELECT_RACES = 0x12;
        int INSERT_RACES = 0x13;
        int DELETE_RACE = 0x14;
        int DELETE_RACES = 0x15;
    }
}

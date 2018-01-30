package mcssoft.com.raceremindermvp.utility;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Utility class to define types of operations to be performed.
 */
public final class OpType {

    @IntDef ({MType.DOWNLOAD_MEETINGS, MType.COUNT_MEETINGS, MType.SELECT_MEETINGS, MType.INSERT_MEETINGS})

    @Retention(RetentionPolicy.SOURCE)

    public @interface MType {
        int DOWNLOAD_MEETINGS = 0x00;
        int COUNT_MEETINGS = 0x01;
        int SELECT_MEETINGS = 0x02;
        int INSERT_MEETINGS = 0x03;
    }
}

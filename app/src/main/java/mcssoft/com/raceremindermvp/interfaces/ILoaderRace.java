package mcssoft.com.raceremindermvp.interfaces;

/**
 * Interface implemented by RaceLoaderManager and passed to RaceLoader constructor so that
 * RaceLoader.deliverResult(data) can be passed back to the RaceLoaderManager.
 */
public interface ILoaderRace {

    void deliverResult(Object data);
}

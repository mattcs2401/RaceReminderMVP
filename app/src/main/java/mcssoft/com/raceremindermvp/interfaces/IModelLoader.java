package mcssoft.com.raceremindermvp.interfaces;

import android.content.Loader;
import android.os.Bundle;

/**
 * Interface to provide Loader operations to the Model.
 */
public interface IModelLoader {

    void onFinished(Loader loader, Object data, Bundle bundle);
}

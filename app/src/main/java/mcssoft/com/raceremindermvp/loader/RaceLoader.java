package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.CursorLoader;

public class RaceLoader extends AsyncTaskLoader<Object> {

    public RaceLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        return null;
    }

    @Override
    public void deliverResult(Object data) {
        super.deliverResult(data);
    }
}

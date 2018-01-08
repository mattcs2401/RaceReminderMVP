package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.CursorLoader;
import android.os.Bundle;

public class RaceLoader extends AsyncTaskLoader<Object> {

    public RaceLoader(Context context, Bundle args) {
        super(context);
        this.args = args;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    @Override
    public Object loadInBackground() {
        return null;
    }

    @Override
    public void deliverResult(Object data) {
        super.deliverResult(data);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }

    private Bundle args;
}
/*
https://developer.android.com/reference/android/content/AsyncTaskLoader.html
 */


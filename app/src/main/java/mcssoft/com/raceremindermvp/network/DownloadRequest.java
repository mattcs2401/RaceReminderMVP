package mcssoft.com.raceremindermvp.network;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.utility.XmlParser;

import static mcssoft.com.raceremindermvp.utility.DbType.TName.MEETINGS;
import static mcssoft.com.raceremindermvp.utility.DbType.TName.RACES;
import static mcssoft.com.raceremindermvp.utility.DbType.TName.RUNNERS;

/**
 * Custom Volley.Request<T> class
 * @param <T>
 */
public class DownloadRequest<T> extends Request<List> {

    public DownloadRequest(int method, String url, Context context, Response.Listener listener,
                           Response.ErrorListener errorListener, String tableName) {
        super(method, url, errorListener);
        this.context = context;
        this.tableName = tableName;
        this.listener = listener;
        this.errorListener = errorListener;
        ButterKnife.bind(this, new View(context));
    }

    // From the doco, runs on a background worker thread.
    @Override
    protected Response<List> parseNetworkResponse(NetworkResponse response) {
        List theResult = null;         // main list of result objects from parsing the Xml.
        XmlParser parser = null;

        InputStream instream = new ByteArrayInputStream(response.data);

        try {
            parser = new XmlParser(context, instream);
            // Parse the response into Meeting, Race or Runner objects.
            switch(tableName) {
                case MEETINGS:
                    theResult = parser.parse(table_meetings);
                    break;
                case RACES:
                    theResult = parser.parse(table_races);
                    break;
                case RUNNERS:
                    theResult = parser.parse(table_runners);
                    break;
            }
        } catch(Exception ex) {
            Log.d(this.getClass().getCanonicalName(), ex.getMessage());
        } finally {
            return Response.success(theResult, null);
        }
    }

    @Override
    protected void deliverResponse(List response) {
        listener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        errorListener.onErrorResponse(error);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Private vars">
    private Context context;                          // context for database operations.
    private String tableName;                         // the affected table.
    private Response.Listener<List> listener;         // non-error listener callback.
    private Response.ErrorListener errorListener;     // error listener callback.

    @BindString(R.string.table_meetings) String table_meetings;
    @BindString(R.string.table_races) String table_races;
    @BindString(R.string.table_runners) String table_runners;
    //</editor-fold>
}

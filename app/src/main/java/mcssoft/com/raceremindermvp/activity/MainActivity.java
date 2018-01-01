package mcssoft.com.raceremindermvp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.dialog.NetworkDialog;
import mcssoft.com.raceremindermvp.fragment.MainFragment;
import mcssoft.com.raceremindermvp.interfaces.IActivityFragment;

public class MainActivity extends AppCompatActivity implements IActivityFragment {

    //<editor-fold defaultstate="collapsed" desc="Region: Lifecycle">
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialise();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Interface">
    @Override
    public void showNoNetworkDialog() {
        networkDialog = new NetworkDialog();
        networkDialog.setShowsDialog(true);
        StringBuilder sb = new StringBuilder();
        sb.append(network_connection_error_p1);
        sb.append(System.getProperty("line.separator"));
        sb.append(System.getProperty("line.separator"));
        sb.append(network_connection_error_p2);
        Bundle bundle = new Bundle();
        bundle.putString(network_dialog_text_key, sb.toString());
        networkDialog.setArguments(bundle);
        networkDialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void showProgressDialog(boolean showProgress) {
        if(showProgress) {
            progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(get_meetings_info);
            progressDialog.show();
        } else {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public ProgressDialog getProgressDialog() { return progressDialog; }

    @Override
    public NetworkDialog getNetworkDialog() { return networkDialog; }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    private void initialise() {
        setContentView(R.layout.activity_main);
        // set Toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tb_main);
        setSupportActionBar(toolbar);
        // set Fragment.
        getFragmentManager().beginTransaction().add(R.id.id_main_fragment_container, new MainFragment()).commit();
        // ButterKnife.
        unbinder = ButterKnife.bind(this);

    }
    //</editor-fold>

    private Unbinder unbinder;              // used to ButterKnife Unbind in the OnDestroy lifecycle method.
    private NetworkDialog networkDialog;    // the network (error) dialog.
    private ProgressDialog progressDialog;  // the progress dialog.

    // Butter Knife
    @BindString(R.string.network_dialog_text_key) String network_dialog_text_key;
    @BindString(R.string.network_connection_error_p1) String network_connection_error_p1;
    @BindString(R.string.network_connection_error_p2) String network_connection_error_p2;
    @BindString(R.string.get_meetings_info) String get_meetings_info;
}

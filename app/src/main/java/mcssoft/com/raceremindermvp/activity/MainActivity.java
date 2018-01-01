package mcssoft.com.raceremindermvp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.dialog.NetworkDialog;
import mcssoft.com.raceremindermvp.fragment.MainFragment;
import mcssoft.com.raceremindermvp.interfaces.IActivityFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IActivityFragment {

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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Listener">
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, NewActivity.class);
        startActivity(intent, null);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Interface">
    @Override
    public void showNetworkDialog() {
        networkDialog = new NetworkDialog();
        networkDialog.setShowsDialog(true);
        Bundle bundle = new Bundle();
        bundle.putString(network_dialog_text_key, network_connection_error);
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
    public ProgressDialog getProgressDialog() {
        return this.progressDialog;
    }

    @Override
    public NetworkDialog getNetworkDialog() {
        return this.networkDialog;
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    private void initialise() {
        setContentView(R.layout.activity_main);
        // set Toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tb_main);
        setSupportActionBar(toolbar);
        // set FAB.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.id_fab);
        fab.setOnClickListener(this);
        // set Fragment.
        getFragmentManager().beginTransaction().add(R.id.id_main_fragment_container, new MainFragment()).commit();
        // ButterKnife.
        ButterKnife.bind(this);

    }
    //</editor-fold>

    private NetworkDialog networkDialog;
    private ProgressDialog progressDialog;

    // Butter Knife
    @BindString(R.string.network_dialog_text_key) String network_dialog_text_key;
    @BindString(R.string.network_connection_error) String network_connection_error;
    @BindString(R.string.get_meetings_info) String get_meetings_info;
}

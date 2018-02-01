package mcssoft.com.raceremindermvp.activity;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.dialog.NetworkDialog;
import mcssoft.com.raceremindermvp.fragment.MeetingFragment;
import mcssoft.com.raceremindermvp.fragment.RaceFragment;
import mcssoft.com.raceremindermvp.interfaces.fragment.IMainActivity;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    //<editor-fold defaultstate="collapsed" desc="Region: Lifecycle">
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set Toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_tb_main);
        setSupportActionBar(toolbar);
        // set Fragment.
        getFragmentManager().beginTransaction().add(R.id.id_main_fragment_container, new MeetingFragment()).commit();
        // ButterKnife.
        unbinder = ButterKnife.bind(this);
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
            // TBA
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

    //<editor-fold defaultstate="collapsed" desc="Region: IMainActivity">
    @Override
    public void showNoNetworkDialog() {
        networkDialog = new NetworkDialog();
        networkDialog.setShowsDialog(true);

        StringBuilder sb = new StringBuilder()
        .append(network_connection_error_l1)
        .append(System.getProperty("line.separator"))
        .append(System.getProperty("line.separator"))
        .append(network_connection_error_l2);

        Bundle bundle = new Bundle();
        bundle.putString(network_dialog_text_key, sb.toString());

        networkDialog.setArguments(bundle);
        networkDialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void showProgressDialog(boolean showProgress, @Nullable String message) {
        if(showProgress) {
            if(progressDialog == null) {
                progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.setMessage(message); //getting_meetings);
            }
            progressDialog.show();
        } else {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public void showRaceFragment(int lPos) {
        Bundle bundle = new Bundle();
        bundle.putInt("key", lPos);
        RaceFragment raceFragment = new RaceFragment();
        raceFragment.setArguments(bundle);
        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
        fragTrans.replace(R.id.id_main_fragment_container, raceFragment);
        fragTrans.addToBackStack(null);
        fragTrans.commit();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    // TBA
    //</editor-fold>

    private Unbinder unbinder;              // used to ButterKnife Unbind in the OnDestroy lifecycle method.
    private NetworkDialog networkDialog;    // the network (error) dialog.
    private ProgressDialog progressDialog;  // the progress dialog.

    // Butter Knife
    @BindString(R.string.network_dialog_text_key) String network_dialog_text_key;
    @BindString(R.string.network_connection_error_p1) String network_connection_error_l1;
    @BindString(R.string.network_connection_error_p2) String network_connection_error_l2;

}

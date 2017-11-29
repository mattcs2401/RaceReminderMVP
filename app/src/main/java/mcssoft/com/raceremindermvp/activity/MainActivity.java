package mcssoft.com.raceremindermvp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

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

    //<editor-fold defaultstate="collapsed" desc="Region: Initialise">
    private void initialise() {
        setContentView(R.layout.activity_main);

        setToolBar();
        setFAB();
        setFragment();
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
    }

    private void setFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.id_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TODO - add new race.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setFragment() {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.layout_fragment_main_key), R.layout.fragment_main);
        mainFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.id_fragment_container, mainFragment).commit();
    }
    //</editor-fold>

}

package mcssoft.com.raceremindermvp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mcssoft.com.raceremindermvp.utility.Resources;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.fragment.NewFragment;

public class NewActivity extends AppCompatActivity {

    //<editor-fold defaultstate="collapsed" desc="Region: Lifecycle">
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialise();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Initialise">
    private void initialise() {
        setContentView(R.layout.activity_new);
        // set Activity title.
        getSupportActionBar().setTitle(Resources.getInstance(this).getString(R.string.new_activity_title));
        // set Fragment.
        NewFragment newFragment = new NewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.layout_fragment_new_key), R.layout.fragment_new);
        newFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.id_fragment_container, newFragment).commit();
    }
    //</editor-fold>

}

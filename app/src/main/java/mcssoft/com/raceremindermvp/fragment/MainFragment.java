package mcssoft.com.raceremindermvp.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mcssoft.com.raceremindermvp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment {

    public MainFragment() { }

    @Override
    protected int getLayout() {
        return layoutId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutId = getArguments().getInt(getString(R.string.layout_fragment_main_key));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        rvRaceListing = getRootView().findViewById(R.id.id_rv_raceListing);

        String bp="";
    }

    private int layoutId;
    private RecyclerView rvRaceListing;

}

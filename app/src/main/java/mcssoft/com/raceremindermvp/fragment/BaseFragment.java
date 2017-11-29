package mcssoft.com.raceremindermvp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Ancestor class for any Fragments used in app.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayout(), container, false);
        return rootView;
    }

    /**
     * Set the layout for child class.
     * @return The layout value (e.g. R.layout.laout_name)
     */
    protected abstract int getLayout();

    /**
     * Provide access to the main view.
     * @return The main view (as set by getLatout().
     */
    public View getRootView() { return rootView; }

    private View rootView;
}

package mcssoft.com.raceremindermvp.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.IViewPresenter;
import mcssoft.com.raceremindermvp.presenter.RacePresenterImpl;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment implements IViewPresenter {

    public MainFragment() { }

    //<editor-fold defaultstate="collapsed" desc="Region: BaseFragment">
    @Override
    protected int getLayout() {
        return layoutId;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Lifecycle">
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        racePresenter = new RacePresenterImpl(this);

        layoutId = getArguments().getInt(getString(R.string.layout_fragment_main_key));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupRecyclerView();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IViewPresenter">
    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    private void setupRecyclerView() {
        rvRaceListing = getRootView().findViewById(R.id.id_rv_raceListing);

        String bp="";
    }
    //</editor-fold>

    private int layoutId;
    private RacePresenterImpl racePresenter;
//    private IPresenterView presenterView;
    private RecyclerView rvRaceListing;

}

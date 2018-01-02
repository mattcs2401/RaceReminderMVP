package mcssoft.com.raceremindermvp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.dialog.NetworkDialog;
import mcssoft.com.raceremindermvp.interfaces.IActivityFragment;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterView;
import mcssoft.com.raceremindermvp.interfaces.mvp.IViewPresenter;
import mcssoft.com.raceremindermvp.presenter.MainPresenterImpl;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements IViewPresenter, IClick.ItemClick {

    //<editor-fold defaultstate="collapsed" desc="Region: Lifecycle">
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Get interface to the Activity.
        iActivityFragment = (IActivityFragment) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // set RecyclerView component first, Presenter, and Model, expect it.
        setRecyclerView();
        iPresenterView = new MainPresenterImpl(this);

        if(iPresenterView.getNetworkCheck()) {
            iActivityFragment.showProgressDialog(true);

            int meetings = iPresenterView.getMeetings();

            iActivityFragment.showProgressDialog(false);
        } else {
            iActivityFragment.showNoNetworkDialog();
            // TODO - start a background service to periodically check for a connection ?
            String bp = "";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IClick.ItemClick">
    @Override
    public void onItemClick(View view, @Nullable int lPos) {
        // Actual TBA
        Snackbar.make(view, "Item " + lPos + " selected.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IViewPresenter">
    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public IClick.ItemClick getClickListener() {
        return this;
    }

    @Override
    public ProgressDialog getProgressDialog() {
        return iActivityFragment.getProgressDialog();
    }

    @Override
    public NetworkDialog getNetworkDialog() {
        return iActivityFragment.getNetworkDialog();
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    private void setRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.scrollToPosition(0);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    //</editor-fold>

    private IPresenterView iPresenterView;

    private IActivityFragment iActivityFragment;

    // Butter Knife.
    private Unbinder unbinder;
    @BindView(R.id.id_rv_meetingListing) RecyclerView recyclerView;

}

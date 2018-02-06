package mcssoft.com.raceremindermvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.fragment.base.BaseFragment;
import mcssoft.com.raceremindermvp.interfaces.fragment.IMainActivity;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;
import mcssoft.com.raceremindermvp.presenter.MainPresenterImpl;

/**
 * A placeholder fragment containing a simple view.
 */
public class MeetingFragment extends BaseFragment {

    //<editor-fold defaultstate="collapsed" desc="Region: Lifecycle">
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // pickup fragment related menu options.
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Get interface to the Activity.
        iMainActivity = (IMainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // set RecyclerView component first, Presenter, and Model, expect it.
        setRecyclerView();
        // set the MainPresenterImpl (in turn sets MeetingModelImpl).
        iPresenterView = new MainPresenterImpl(this, null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_meeting, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //</editor-fold>

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.id_delete_meetings:
                deleteMeetings();
                refreshDisplay();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterView">
    @Override
    public void deleteMeetings() {
        iPresenterView.deleteMeetings();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IClick.ItemClick">
    @Override
    public void onItemClick(View view, @Nullable int lPos) {
        iMainActivity.showRaceFragment(lPos);
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
    public void showProgressDialog(boolean show, String message) {
        iMainActivity.showProgressDialog(show, message);
    }

    @Override
    public void showNoNetworkDialog() {
        iMainActivity.showNoNetworkDialog();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    private void refreshDisplay() {
        // TODO - refresh the view.
        String bp = "";
    }

    private void setRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.scrollToPosition(0);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    //</editor-fold>

    // Butter Knife.
    private Unbinder unbinder;
    @BindView(R.id.id_rv_meetingListing) RecyclerView recyclerView;
}

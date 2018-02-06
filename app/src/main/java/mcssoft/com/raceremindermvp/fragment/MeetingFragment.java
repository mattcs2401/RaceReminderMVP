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
        chgDelToAdd = false;
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
        delMenuItem = menu.findItem(R.id.id_delete_meetings);
        addMenuItem = menu.findItem(R.id.id_add_meetings);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //</editor-fold>


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        // Note: this is called every time so need to keep as simple as possible.
        super.onPrepareOptionsMenu(menu);
        if(chgDelToAdd) {
            // insert Add
            addMenuItem.setVisible(true);
            delMenuItem.setVisible(false);
        } else {
            // insert Delete
            addMenuItem.setVisible(false);
            delMenuItem.setVisible(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.id_delete_meetings:
                deleteMeetings();
                clearDisplay();
                chgDelToAdd = true;
                return true;
            case R.id.id_add_meetings:
                downloadMeetings();
                chgDelToAdd = false;
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

    @Override
    public void downloadMeetings() {
        iPresenterView.downloadMeetings();
    }

    @Override
    public void clearDisplay() {
        iPresenterView.clearDisplay();
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
    private void setRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.scrollToPosition(0);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    //</editor-fold>

    private boolean chgDelToAdd;       // flag, change Delete menu item to Add.
    private MenuItem delMenuItem;      // menu option Delete.
    private MenuItem addMenuItem;      // menu option Add.

    // Butter Knife.
    private Unbinder unbinder;
    @BindView(R.id.id_rv_meetingListing) RecyclerView recyclerView;
}

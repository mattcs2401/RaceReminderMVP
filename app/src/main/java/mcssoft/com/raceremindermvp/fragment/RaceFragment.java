package mcssoft.com.raceremindermvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.fragment.base.BaseFragment;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;
import mcssoft.com.raceremindermvp.interfaces.fragment.IMainActivity;
import mcssoft.com.raceremindermvp.model.database.Race;
import mcssoft.com.raceremindermvp.presenter.MainPresenterImpl;

public class RaceFragment extends BaseFragment {
// BaseFragment extends Fragment implements IViewPresenter, IPresenterView, IClick.ItemClick

    //<editor-fold defaultstate="collapsed" desc="Region: Lifecycle">
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Get interface to the Activity.
        iMainActivity = (IMainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_race, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // set RecyclerView component first, Presenter, and Model, expect it.
        setRecyclerView();
        // set the MainPresenterImpl (in turn sets RaceModelImpl).
        iPresenterViewRace = new MainPresenterImpl(this, getArguments());
        // set a header row in the recyclerview.
//        setRecyclerViewHeader();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IPresenterView.IRace">
    @Override
    public void deleteRaces() {
        iPresenterViewRace.deleteRaces();
    }

    @Override
    public void downloadRaces() {
        iPresenterViewRace.downloadRaces();
    }

    @Override
    public void clearRaceDisplay() {
        iPresenterViewRace.clearRaceDisplay();
    }

    @Override
    public Race getRace(int lPos) {
        return iPresenterViewRace.getRace(lPos);
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

    //<editor-fold defaultstate="collapsed" desc="Region: IClick.ItemClick">
    @Override
    public void onItemClick(View view, @Nullable int lPos) {
        Snackbar.make(view, "Race item " + lPos + " selected.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
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

    private void setRecyclerViewHeader() {
        List<String> lMeetingInfo = iPresenterViewRace.getMeetingInfo();
        meetingCode.setText(lMeetingInfo.get(0));
        venueName.setText(lMeetingInfo.get(1));
        trackRating.setText(lMeetingInfo.get(2));
        weatherDesc.setText(lMeetingInfo.get(3));
    }
    //</editor-fold>

    // Butter Knife.
    private Unbinder unbinder;

    @BindView(R.id.id_tv_meeting_code) TextView meetingCode;
    @BindView(R.id.id_tv_venue_name) TextView venueName;
    @BindView(R.id.id_tv_track_rating) TextView trackRating;
    @BindView(R.id.id_tv_weather_desc) TextView weatherDesc;
    @BindView(R.id.id_rv_race_listing) RecyclerView recyclerView;
}

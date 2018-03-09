package mcssoft.com.raceremindermvp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import mcssoft.com.raceremindermvp.fragment.base.BaseFragment;
import mcssoft.com.raceremindermvp.interfaces.fragment.IMainActivity;
import mcssoft.com.raceremindermvp.presenter.MainPresenterImpl;

public class RunnerFragment extends BaseFragment {

    //<editor-fold defaultstate="collapsed" desc="Region: Lifecycle">
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Get interface to the Activity.
        iMainActivity = (IMainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_runner, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // set RecyclerView component first, Presenter, and Model, expect it.
        setRecyclerView();
        // set the MainPresenterImpl (in turn sets RaceModelImpl).
//        iPresenterViewRace = new MainPresenterImpl(this, getArguments());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

    // Butter Knife.
    private Unbinder unbinder;

    @BindView(R.id.id_rv_runner_listing) RecyclerView recyclerView;
}

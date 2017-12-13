package mcssoft.com.raceremindermvp.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.adapter.RaceAdapter;
import mcssoft.com.raceremindermvp.interfaces.IClick;
import mcssoft.com.raceremindermvp.interfaces.IPresenterView;
import mcssoft.com.raceremindermvp.interfaces.IViewPresenter;
import mcssoft.com.raceremindermvp.presenter.RacePresenterImpl;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment implements IViewPresenter, IClick.ItemClick {

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

        layoutId = getArguments().getInt(getString(R.string.layout_fragment_main_key));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // set RecyclerView component first, Presenter, and Model, expect it.
        setRecyclerView();
//        setRaceAdapter();
        racePresenterImpl = new RacePresenterImpl(this);
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
    public IPresenterView getPresenter(IPresenterView iPresenterView) {
        this.iPresenterView = iPresenterView;
        return iPresenterView;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    private void setRecyclerView() {
        recyclerView = getActivity().findViewById(R.id.id_rv_raceListing);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.scrollToPosition(0);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    //</editor-fold>

    private int layoutId;
    private RacePresenterImpl racePresenterImpl;
    private IPresenterView iPresenterView;
    private RecyclerView recyclerView;

}

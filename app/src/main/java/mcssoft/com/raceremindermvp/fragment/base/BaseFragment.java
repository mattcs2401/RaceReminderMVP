package mcssoft.com.raceremindermvp.fragment.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mcssoft.com.raceremindermvp.interfaces.click.IClick;
import mcssoft.com.raceremindermvp.interfaces.fragment.IMainActivity;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterView;
import mcssoft.com.raceremindermvp.interfaces.mvp.IViewPresenter;

public abstract class BaseFragment extends Fragment implements IViewPresenter, IClick.ItemClick {

    //<editor-fold defaultstate="collapsed" desc="Region: Lifecycle">
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IViewPresenter">
    @Override
    public void onItemClick(View view, @Nullable int lPos) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return null;
    }

    @Override
    public void showProgressDialog(boolean show, @Nullable String message) {

    }

    @Override
    public void showNoNetworkDialog() {

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IClick.ItemClick">
    @Override
    public IClick.ItemClick getClickListener() {
        return null;
    }
    //</editor-fold>

    protected IPresenterView iPresenterView;
    protected IMainActivity iMainActivity;
}

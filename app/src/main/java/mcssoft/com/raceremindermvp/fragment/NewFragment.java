package mcssoft.com.raceremindermvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterView;
import mcssoft.com.raceremindermvp.presenter.MainPresenterImpl;

/**
 *
 */
public class NewFragment extends BaseFragment {//} implements IViewPresenter, IClick.ItemClick {

    public NewFragment() { }

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

        layoutId = getArguments().getInt(getString(R.string.layout_fragment_new_key));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    //</editor-fold>

//    //<editor-fold defaultstate="collapsed" desc="Region: IViewPresenter">
//    @Override
//    public Context getContext() {
//        return getActivity().getApplicationContext();
//    }
//
//    @Override
//    public RecyclerView getRecyclerView() {
//        return recyclerView;
//    }
//
//    @Override
//    public IClick.ItemClick getClickListener() {
//        return this;
//    }
//
//    @Override
//    public IPresenterView getPresenter(IPresenterView iPresenterView) {
//        this.iPresenterView = iPresenterView;
//        return iPresenterView;
//    }
//    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    //</editor-fold>

    private int layoutId;
    private MainPresenterImpl racePresenterImpl;
    private IPresenterView iPresenterView;

}

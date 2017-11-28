package mcssoft.com.raceremindermvp.interfaces;

public interface IPresenter <V> {

    void attachedView(V view);

    void detachView();

    void onResume();

    void onItemSelected(int position);
}

package mcssoft.com.raceremindermvp.interfaces;


import java.util.ArrayList;

import mcssoft.com.raceremindermvp.model.Race;

public interface IRaceListView {

    void setItems(ArrayList<Race> raceList);

    void showProgress();

    void hideProgress();

    void showMessage(String message);
}

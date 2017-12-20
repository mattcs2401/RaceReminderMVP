package mcssoft.com.raceremindermvp.fragment;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.dialog.TimePickDialog;

/**
 *
 */
public class NewFragment extends BaseFragment implements View.OnClickListener {

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
        View view = getRootView();
        // edit text.
        etCityCode = (EditText) view.findViewById(R.id.id_et_city_code);
        etRaceCode = (EditText) view.findViewById(R.id.id_et_race_code);
        etRaceNum = (EditText) view.findViewById(R.id.id_et_race_num);
        etRaceSel = (EditText) view.findViewById(R.id.id_et_race_sel);
        // buttons.
        btnCancel = (Button) view.findViewById(R.id.id_btn_cancel);
        btnSave = (Button) view.findViewById(R.id.id_btn_save);
        btnRaceTime = (Button) view.findViewById(R.id.id_btn_race_time);
        // set listeners
        btnRaceTime.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Listener">
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn_cancel:
                getActivity().finish();
                break;
            case R.id.id_btn_save:
                Snackbar.make(view, "TODO - Save button pressed.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
                break;
            case R.id.id_btn_race_time:
                DialogFragment df = new TimePickDialog();
                df.show(getFragmentManager(), "TimePicker");
                break;

        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    public long[] getTimeDetail(long[] timeDetail) {
        return timeDetail;
    }
    //</editor-fold>

    private int layoutId;
    private EditText etCityCode;
    private EditText etRaceCode;
    private EditText etRaceNum;
    private EditText etRaceSel;
    private Button btnRaceTime;
    private Button btnCancel;
    private Button btnSave;
}

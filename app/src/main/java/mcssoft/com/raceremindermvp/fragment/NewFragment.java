package mcssoft.com.raceremindermvp.fragment;

import android.app.Fragment;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.dialog.TimePickDialog;
import mcssoft.com.raceremindermvp.utility.Resources;
import mcssoft.com.raceremindermvp.utility.DateTime;

/**
 *
 */
public class NewFragment extends Fragment
        implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    //<editor-fold defaultstate="collapsed" desc="Region: Lifecycle">
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        View view = getRootView();
        // edit text.
//        etCityCode = (EditText) view.findViewById(R.id.id_et_city_code);
//        etRaceCode = (EditText) view.findViewById(R.id.id_et_race_code);
//        etRaceNum = (EditText) view.findViewById(R.id.id_et_race_num);
//        etRaceSel = (EditText) view.findViewById(R.id.id_et_race_sel);
//        // buttons.
//        btnCancel = (Button) view.findViewById(R.id.id_btn_cancel);
//        btnSave = (Button) view.findViewById(R.id.id_btn_save);
//        btnRaceTime = (Button) view.findViewById(R.id.id_btn_race_time);
//        // set listeners
//        btnRaceTime.setOnClickListener(this);
//        btnCancel.setOnClickListener(this);
//        btnSave.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Listener">
    // View interface OnClickListener returns here.
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn_cancel:
                getActivity().finish();
                break;
            case R.id.id_btn_save:
                collateValues();
                break;
            case R.id.id_btn_race_time:
                showTimePickerDialog();
                break;

        }
    }

    // TimePickerDialog interface OnTimeSetListener returns here.
    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        btnRaceTime.setText(formatTime(hour, minute));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    private void collateValues() {
        if(checkFieldLengths()) {

        } else {
            doSnackError(Resources.getInstance(getActivity()).getString(R.string.all_fields_req));
        }
    }
    /**
     * Simple sanity check there is something in all the fields.
     * @return True if values exist in all fields.
     */
    private boolean checkFieldLengths() {
        if ((etCityCode.getText().length() > 0) &&
                (etRaceCode.getText().length() > 0) &&
                (etRaceNum.getText().length() > 0) &&
                (etRaceSel.getText().length() > 0) &&
                (btnRaceTime.getText().length() > 0)) {
            return true;
        }
        return false;
    }

    /**
     * Show the TimePicker dialog with the current time.
     */
    private void showTimePickerDialog() {
        final Calendar calendar = DateTime.getInstance(getActivity()).getCalendar();
        TimePickerDialog tpd = new TimePickDialog(
                getActivity(),
                this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);
        tpd.show();
    }

    /**
     * Format time for display as HH:MM.
     * @param hour The hour.
     * @param minute The minute.
     * @return DateTime as HH:MM
     */
    private String formatTime(int hour, int minute) {
        return DateTime.getInstance(getActivity())
                .getFormattedTimeFromMillis(DateTime.getInstance(getActivity())
                        .getMillisFromTimeComponent(new int[] {hour, minute}));
    }

    private void doSnackError(String message) {
//        Snackbar snackbar = Snackbar.make(getRootView(), message, Snackbar.LENGTH_LONG)
//                .setAction("Action", null);
//        snackbar.getView().setBackgroundColor(Color.RED);
//        TextView textView = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
//        textView.setTextColor(Color.WHITE);
//        snackbar.show();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Private vars">
    private EditText etCityCode;
    private EditText etRaceCode;
    private EditText etRaceNum;
    private EditText etRaceSel;
    private Button btnRaceTime;
    private Button btnCancel;
    private Button btnSave;

    // Butter Knife
    private Unbinder unbinder;
    //</editor-fold>
}

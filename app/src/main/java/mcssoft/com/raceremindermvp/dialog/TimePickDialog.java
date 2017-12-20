package mcssoft.com.raceremindermvp.dialog;

import android.app.TimePickerDialog;
import android.content.Context;

public class TimePickDialog extends TimePickerDialog {

    public TimePickDialog(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
}

package mcssoft.com.raceremindermvp.dialog;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.utility.Resources;

public class TimePickDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public TimePickDialog(Handler handler) {
        Bundle bundle = getArguments();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current time as the default values for the time picker.
        final Calendar calendar = Calendar.getInstance();
        long timeMillis = calendar.getTimeInMillis();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        timeDetail = new long[3];
        timeDetail[0] = timeMillis;
        timeDetail[1] = hour;
        timeDetail[2] = minute;

        //Create and return a new instance of TimePickerDialog
        return new TimePickerDialog(
                getActivity(),
               this,
                hour,
                minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        // TODO - put "host" name in a Bundle.
//        Fragment hostFragment = getFragmentManager().findFragmentByTag("NewFragment");
//        hostFragment.getTimeDetail(timeDetail);
        String bp = "";
    }

    private long[] timeDetail;
}

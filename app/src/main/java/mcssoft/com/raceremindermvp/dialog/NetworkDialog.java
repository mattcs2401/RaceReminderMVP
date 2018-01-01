package mcssoft.com.raceremindermvp.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.utility.Resources;

public class NetworkDialog extends DialogFragment
        implements DialogInterface.OnClickListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setIcon(R.drawable.ic_action_warning)
                .setTitle(Resources.getInstance(getContext()).getString(R.string.title_dialog_network))
                .setMessage(getArguments().getString(Resources.getInstance(getContext())
                .getString(R.string.network_dialog_text_key)))
                .setPositiveButton(R.string.button_ok_text, this);
        return dialog.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch(which) {
            case Dialog.BUTTON_POSITIVE:
                break;
        }
    }
}

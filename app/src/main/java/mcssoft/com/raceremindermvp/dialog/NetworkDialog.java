package mcssoft.com.raceremindermvp.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;

public class NetworkDialog extends DialogFragment
        implements DialogInterface.OnClickListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ButterKnife.bind(this, new View(getContext()));
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setIcon(R.drawable.ic_action_warning)
                .setTitle(title_dialog_network)
                .setMessage(getArguments().getString(network_dialog_text_key))
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

    @BindString(R.string.title_dialog_network) String title_dialog_network;
    @BindString(R.string.network_dialog_text_key) String network_dialog_text_key;
}

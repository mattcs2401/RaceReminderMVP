package mcssoft.com.raceremindermvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.IClick;

public class RaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public RaceViewHolder(View itemView) {
        super(itemView);
//        tvEmpty = (TextView) view.findViewById(R.id.id_tv_empty);
//        tvEmpty.setText(Resources.getInstance().getString(R.string.nothing_to_show));
    }

    public RaceViewHolder(View view, IClick.ItemClick icListener) {
        super(view);
//        tvEmpty = (TextView) view.findViewById(R.id.id_tv_empty);
//        tvEmpty.setText(Resources.getInstance().getString(R.string.nothing_to_show));

        this.icListener = icListener;
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition(); // debug/testing purposes.
        icListener.onItemClick(view, position);
    }

    private IClick.ItemClick icListener;
}

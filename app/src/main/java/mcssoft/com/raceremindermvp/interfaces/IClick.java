package mcssoft.com.raceremindermvp.interfaces;

import android.view.View;

public interface IClick {
    /**
     *
     */
    public interface ItemClick {
        void onItemClick(View view, int lPos);
    }

    public interface ItemLongClick {
        void onItemClick(View view, int lPos);
    }

}

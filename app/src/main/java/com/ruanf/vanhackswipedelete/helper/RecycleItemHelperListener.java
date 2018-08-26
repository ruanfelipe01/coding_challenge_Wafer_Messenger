package com.ruanf.vanhackswipedelete.helper;

import android.support.v7.widget.RecyclerView;

public interface RecycleItemHelperListener {

    void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int position);
}

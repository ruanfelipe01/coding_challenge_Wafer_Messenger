package com.ruanf.vanhackswipedelete.helper;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.ruanf.vanhackswipedelete.adapter.Adapter;

public class RecycleItemHelper extends ItemTouchHelper.SimpleCallback {

    private RecycleItemHelperListener listener;


    public RecycleItemHelper(int dragDirs, int swipeDirs, RecycleItemHelperListener listener) {
        super( dragDirs, swipeDirs );
        this.listener = listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return true ;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        if (listener!= null)
        {
            listener.onSwipe( viewHolder, direction, viewHolder.getAdapterPosition() );
        }
    }

    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {
       View foregroundView = ((Adapter.MyViewHolder)viewHolder).viewForeground;
       getDefaultUIUtil().clearView( foregroundView );
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundView = ((Adapter.MyViewHolder)viewHolder).viewForeground;
        getDefaultUIUtil().onDraw( c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive );
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View foregroundView = ((Adapter.MyViewHolder) viewHolder).viewForeground;
        getDefaultUIUtil().onDrawOver( c, recyclerView, foregroundView, dX, dY, actionState, isCurrentlyActive );
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if(viewHolder != null) {
            View foregroundView = ((Adapter.MyViewHolder) viewHolder).viewForeground;
            getDefaultUIUtil().onSelected( foregroundView );
        }
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection( flags, layoutDirection );
    }
}

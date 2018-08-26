package com.ruanf.vanhackswipedelete.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruanf.vanhackswipedelete.R;
import com.ruanf.vanhackswipedelete.model.Item;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

   private Context context;
   private List<Item> list;

    public Adapter(Context context, List<Item> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from( viewGroup.getContext() )
                .inflate( R.layout.card_list_item,viewGroup, false );

        return new MyViewHolder( itemView );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        final Item item = list.get( i );
        myViewHolder.name.setText( item.getName() );
        myViewHolder.language.setText( item.getLanguage() );
        myViewHolder.currency.setText( item.getCurrency() );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int position)
    {
        list.remove( position );
        notifyItemRemoved( position );
    }

    public void restoreItem(Item item, int position)
    {
        list.add( position, item );
        notifyItemInserted( position );

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name, language, currency;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            name = itemView.findViewById( R.id.name );
            language = itemView.findViewById( R.id.language );
            currency = itemView.findViewById( R.id.currency );
            viewBackground = itemView.findViewById( R.id.view_background );
            viewForeground = itemView.findViewById( R.id.view_foreground );
        }
    }
}

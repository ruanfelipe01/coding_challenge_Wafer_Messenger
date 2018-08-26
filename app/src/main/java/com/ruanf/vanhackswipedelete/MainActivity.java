package com.ruanf.vanhackswipedelete;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.ruanf.vanhackswipedelete.adapter.Adapter;
import com.ruanf.vanhackswipedelete.helper.Common;
import com.ruanf.vanhackswipedelete.helper.RecycleItemHelper;
import com.ruanf.vanhackswipedelete.helper.RecycleItemHelperListener;
import com.ruanf.vanhackswipedelete.model.Item;
import com.ruanf.vanhackswipedelete.remote.IMenuRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecycleItemHelperListener {

    private final String URL_REST="https://restcountries.eu/rest/v2/all";
    private RecyclerView recyclerView;
    private List<Item> list;
    private Adapter adapter;
    private CoordinatorLayout rootLayout;

    IMenuRequest mService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mService = Common.getMenuRequest();

        Toolbar toolbar = (Toolbar)findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setTitle( "Vanhack Wafer" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        recyclerView = (RecyclerView)findViewById( R.id.recycler_view );
        rootLayout = (CoordinatorLayout)findViewById( R.id.rootLayout );
        list = new ArrayList<>();
        adapter = new Adapter( this, list );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        //recyclerView.addItemDecoration( new DividerItemDecoration( this, DividerItemDecoration.VERTICAL ) );
        recyclerView.setAdapter( adapter );

        ItemTouchHelper.SimpleCallback itemTSimpleCallback = new RecycleItemHelper( 0,ItemTouchHelper.LEFT, this );

        new ItemTouchHelper( itemTSimpleCallback ).attachToRecyclerView( recyclerView );

        //request API
        addItemToCart();
        
    }

    private void addItemToCart() {
        mService.getMenuList( URL_REST ).enqueue( new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                list.clear();//remove old item
                list.addAll( response.body() );
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        } );
    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if(viewHolder instanceof  Adapter.MyViewHolder)
        {
            String name = list.get( viewHolder.getAdapterPosition() ).getName();
            final Item deletedItem = list.get( viewHolder.getAdapterPosition() );
            final int deleteIndex = viewHolder.getAdapterPosition();

            adapter.removeItem( deleteIndex );

            Snackbar snackbar = Snackbar.make( rootLayout, name + "removed item !" , Snackbar.LENGTH_SHORT );
            snackbar.setAction( "UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.restoreItem( deletedItem,deleteIndex );
                }
            });
            snackbar.setActionTextColor( Color.YELLOW );
            snackbar.show();
        }

    }
}

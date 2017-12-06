package com.appants.adspro;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appants.adspro.rest.UserNetworkResponse;

import java.util.List;

/**
 * Created by Vishnu on 05-12-2017.
 */

public class LevelsListAdapter extends RecyclerView.Adapter<LevelsListAdapter.MyViewHolder> {

    private List<UserNetworkResponse> reqList;
    UserNetworkResponse.ReqResults result;
    UserNetworkResponse load;
    Context cont;
    RecyclerView recyView;

    String sTypes;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_mem,tv_amt;

        public MyViewHolder(View view) {
            super(view);
        }
    }

    public LevelsListAdapter(UserNetworkResponse load, Context context, RecyclerView rcv) {
        // this.reqList = reqList;
        this.load = load;
        this.cont = context;
        this.recyView = rcv;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.networklist_inf, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // CustomerRequestBean movie = result.get(position);

        // holder.custDistance.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return load.result.size();
    }





}

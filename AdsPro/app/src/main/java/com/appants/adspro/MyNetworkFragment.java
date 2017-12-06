package com.appants.adspro;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appants.adspro.rest.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyNetworkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyNetworkFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ApiInterface apiInterface;
    ProgressDialog progressDialog=null;
    public MyNetworkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyNetworkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyNetworkFragment newInstance(String param1, String param2) {
        MyNetworkFragment fragment = new MyNetworkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private RecyclerView recyclerView;
    private LevelsListAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



        JSONObject paramObject = new JSONObject();
        try {
            paramObject.put("user_id","1") ;

        } catch (JSONException e) {
            e.printStackTrace();
        }


     /*   progressDialog=new ProgressDialog(HomeActivity.ctx);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();*/

        Call<String> userCall = apiInterface.getLevelStatus(paramObject.toString());

        userCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Test","onResponse"+response);

                //    progressDialog.dismiss();
                if(response!=null)
                {
                    try {
                        JSONObject object=new JSONObject(response.body());

                        Log.v("TAG....","Result..."+object.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("Test","onFailure"+t);
                //  progressDialog.dismiss();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_network, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);








        return view;
    }


}

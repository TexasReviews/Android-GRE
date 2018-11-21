package in.texasreview.gre.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.SubVideosModel;
import in.texasreview.gre.R;
import in.texasreview.gre.adapters.SubVideosAdapter;
import in.texasreview.gre.helpers.RetrofitServiceGenerator;
import in.texasreview.gre.interfaces.FragmentUpdate;
import in.texasreview.gre.interfaces.ManageServices;
import in.texasreview.gre.interfaces.RecyclerviewOnclick;
import in.texasreview.gre.session.SessionManager;
import in.texasreview.gre.utils.AppConstants;
import in.texasreview.gre.utils.MyProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
@SuppressLint("ValidFragment")
public class SubVideos extends Fragment implements RecyclerviewOnclick {

    @BindView(R.id.rlSubVideos)
    RecyclerView rvSubVideos;

    @BindView(R.id.imvBack)
    ImageView imvBack;

    ArrayList<SubVideosModel> subVideosList;
    String trackId;
    FragmentUpdate fragmentUpdate;
    MyProgressBar myProgressBar;

    @SuppressLint("ValidFragment")
    public SubVideos(FragmentUpdate fragmentUpdate) {
        this.fragmentUpdate = fragmentUpdate;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            trackId = bundle.getString(AppConstants.TRACKIDKEY);
            Log.i("trackId", trackId);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_videos, container, false);
        ButterKnife.bind(this, view);
        SessionManager sessionManager = new SessionManager(getContext());
        getSubVideos(trackId, sessionManager.getUSER_Id());
        myProgressBar = new MyProgressBar(getContext());
        myProgressBar.show();


        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().onBackPressed();

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onClick(int position, String videoOrTest) {

        if (videoOrTest.equalsIgnoreCase("video")) {
            fragmentUpdate.onVideoClick(subVideosList.get(position).getYoutube_uniqid(), subVideosList.get(position).getId(), subVideosList.get(position).getCatid());
        } else {
            fragmentUpdate.onTestClick(subVideosList.get(position).getTestid(), subVideosList.get(position).getId());
        }
    }

    /*This method for get videos service call */
    private void getSubVideos(String trackId, String userId) {

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<ArrayList<SubVideosModel>> call = service.getSubVideos(trackId, userId);
        call.enqueue(new Callback<ArrayList<SubVideosModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SubVideosModel>> call, Response<ArrayList<SubVideosModel>> response) {

                myProgressBar.hide();

                subVideosList = response.body();
                rvSubVideos.setLayoutManager(new GridLayoutManager(getContext(), 2));
                SubVideosAdapter videosAdapter = new SubVideosAdapter(getContext(), subVideosList, SubVideos.this);
                rvSubVideos.setAdapter(videosAdapter);
                rvSubVideos.setItemAnimator(new DefaultItemAnimator());

            }

            @Override
            public void onFailure(Call<ArrayList<SubVideosModel>> call, Throwable t) {

                myProgressBar.hide();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}




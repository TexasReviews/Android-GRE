package in.texasreview.gre.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.LoginResModel;
import in.texasreview.gre.Models.VideosModel;
import in.texasreview.gre.R;
import in.texasreview.gre.adapters.VideosAdapter;
import in.texasreview.gre.helpers.RetrofitServiceGenerator;
import in.texasreview.gre.interfaces.FragmentUpdate;
import in.texasreview.gre.interfaces.ManageServices;
import in.texasreview.gre.interfaces.RecyclerviewOnclick;
import in.texasreview.gre.utils.MyProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class Videos extends Fragment implements RecyclerviewOnclick {

    ArrayList<VideosModel> videosList = new ArrayList();

    FragmentUpdate fragmentUpdate ;

    @BindView(R.id.rvVideos)
    RecyclerView rvVideos;

    @SuppressLint("ValidFragment")
    public Videos(FragmentUpdate fragmentUpdate) {
        this.fragmentUpdate = fragmentUpdate;
    }

    MyProgressBar myProgressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        ButterKnife.bind(this, view);
        myProgressBar = new MyProgressBar(getContext());
        myProgressBar.show();

        getVideos();

        return view;

    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    /*this method for get videos service call */
    private void getVideos() {

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<List<VideosModel>> call = service.getVideos();
        call.enqueue(new Callback<List<VideosModel>>() {
            @Override
            public void onResponse(Call<List<VideosModel>> call, Response<List<VideosModel>> response) {

                myProgressBar.hide();

                 videosList = (ArrayList<VideosModel>) response.body();
                //  Log.i("videosmodel", videosModel.toString());

                rvVideos.setLayoutManager(new GridLayoutManager(getContext(), 2));
                VideosAdapter videosAdapter = new VideosAdapter(getContext(), videosList,Videos.this);
                rvVideos.setAdapter(videosAdapter);
                rvVideos.setItemAnimator(new DefaultItemAnimator());


            }

            @Override
            public void onFailure(Call<List<VideosModel>> call, Throwable t) {

                myProgressBar.hide();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(int position,String videoOrTest) {

        fragmentUpdate.onPositionClick(videosList.get(position).getTrackid());

    }
}

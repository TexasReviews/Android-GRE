package in.texasreview.gre.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.R;
import in.texasreview.gre.session.SessionManager;

import static in.texasreview.gre.activities.NavigationDrawer.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link UserProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserProfile extends Fragment {


    @BindView(R.id.tvId)
    TextView tvId;

    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvMailId)
    TextView tvMailId;

    @BindView(R.id.tvMobileNum)
    TextView tvMobileNum;

    @BindView(R.id.tvBranch)
    TextView tvBranch;

    @BindView(R.id.tvNationality)
    TextView tvNationality;


    public UserProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static UserProfile newInstance(String param1, String param2) {
        UserProfile fragment = new UserProfile();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this,view);
        SessionManager sessionManager = new SessionManager(getContext());
        tvId.setText(sessionManager.getUSER_Id());
        tvName.setText(sessionManager.getFULLNAME());
        tvMailId.setText(sessionManager.getEmail());
        tvMobileNum.setText(sessionManager.getMOBILENUM());
        tvBranch.setText(sessionManager.getBRANCH());

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


}

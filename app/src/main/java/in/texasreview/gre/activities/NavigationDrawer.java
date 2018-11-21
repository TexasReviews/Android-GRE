package in.texasreview.gre.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import in.texasreview.gre.R;
import in.texasreview.gre.fragments.Dashboard;
import in.texasreview.gre.fragments.FeedBackForm;
import in.texasreview.gre.fragments.Player;
import in.texasreview.gre.fragments.PracticeQustions;
import in.texasreview.gre.fragments.PracticeTest;
import in.texasreview.gre.fragments.Profile;
import in.texasreview.gre.fragments.Questions;
import in.texasreview.gre.fragments.Review;
import in.texasreview.gre.fragments.SubVideos;
import in.texasreview.gre.fragments.Videos;
import in.texasreview.gre.interfaces.FragmentUpdate;
import in.texasreview.gre.session.SessionManager;
import in.texasreview.gre.utils.AppConstants;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentUpdate {

    public static final String Fragment = "fragment";

    CircleImageView imvProfilePic;

    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;

    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CircleImageView imvProfilePic = toolbar.findViewById(R.id.imvProfilePic);

        imvProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showProfileDialog();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        bindviewsinHeader();

        getDashboard();
    }

    private void bindviewsinHeader(){


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.removeHeaderView(navigationView.getHeaderView(0));
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_navigation_drawer);
        TextView user_fullname = headerLayout.findViewById(R.id.tvFullName);

        sessionManager = new SessionManager(this);
        user_fullname.setText(sessionManager.getFULLNAME());

        ImageView imvOnlineOrOffline = headerLayout.findViewById(R.id.imvOnlineOrOffline);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Dashboard) {

            getDashboard();


        } else if (id == R.id.nav_PracticeTest) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            PracticeTest practiceTest = new PracticeTest(this);
            fragmentTransaction.replace(R.id.fragment_frame, practiceTest, Fragment);
            fragmentTransaction.addToBackStack(Dashboard.class.getName());
            fragmentTransaction.commit();

        } else if (id == R.id.nav_Videos) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Videos videos = new Videos(this);
            fragmentTransaction.replace(R.id.fragment_frame, videos, Fragment);
            fragmentTransaction.addToBackStack(Dashboard.class.getName());
            fragmentTransaction.commit();
        }
        /*else if (id == R.id.nav_FullLengthTest) {

        }*/
        else if (id == R.id.nav_FeedBackForm) {

            getFeedBackFoam();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onPositionClick(String trackId) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.TRACKIDKEY, trackId);
        SubVideos subVideos = new SubVideos(this);
        subVideos.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_frame, subVideos, Fragment);
        fragmentTransaction.addToBackStack(Videos.class.getName());
        fragmentTransaction.commit();

    }


    @Override
    public void onVideoClick(String videoUrl, String id, String catID) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.VIDEOURLKEY,videoUrl);
        bundle.putString(AppConstants.SUBCATIDKEY,id);
        bundle.putString(AppConstants.CATIDKEY,catID);
        Player player = new Player();
        player.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_frame,player, Fragment);
        fragmentTransaction.addToBackStack(SubVideos.class.getName());
        fragmentTransaction.commit();

    }

    @Override
    public void onTestClick(String testId,String videoId) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.TESTIDKEY,testId);
        bundle.putString(AppConstants.VIDEOIDKEY,videoId);
        Questions questions = new Questions();
        questions.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_frame, questions, Fragment);
        fragmentTransaction.addToBackStack(SubVideos.class.getName());
        fragmentTransaction.commit();

    }

    @Override
    public void onPracticetestClick(ArrayList<String> idsList,String session) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(AppConstants.PRACTICETESTIDSKEY,idsList);
        bundle.putString(AppConstants.SESSIONKEY,session);
        PracticeQustions practiceQustions = new PracticeQustions(this);
        practiceQustions.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_frame, practiceQustions, Fragment);
        fragmentTransaction.addToBackStack(PracticeTest.class.getName());
        fragmentTransaction.commit();



    }

    @Override
    public void onSubmitClick() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Review review = new Review();
        fragmentTransaction.replace(R.id.fragment_frame, review, Fragment);
        fragmentTransaction.addToBackStack(PracticeTest.class.getName());
        fragmentTransaction.commit();


    }

    /*this method for call dashboard fragment*/
    private void getDashboard() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame, Dashboard.newInstance(), Fragment);
        fragmentTransaction.commit();

    }

    private void showProfileDialog() {

        dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.profile_dialog, null);
        dialogBuilder.setView(dialogView);

        CircleImageView cimvProfile = dialogView.findViewById(R.id.cimvProfile);

        TextView name = dialogView.findViewById(R.id.tvName);
        name.setText(sessionManager.getFULLNAME());
        TextView mailid = dialogView.findViewById(R.id.tvmailid);
        mailid.setText(sessionManager.getEmail());
        Button btnProfile = dialogView.findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_frame, Profile.newInstance(), Fragment);
                fragmentTransaction.addToBackStack(Dashboard.class.getName());
                fragmentTransaction.commit();
                alertDialog.dismiss();
            }
        });
        Button btnLogout = dialogView.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logout();

            }
        });
        alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void logout(){

        sessionManager.clearSession();
        alertDialog.dismiss();
        Intent login  = new Intent(this,Login.class);
        startActivity(login);


    }

    private void getFeedBackFoam() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame, FeedBackForm.newInstance(), Fragment);
        fragmentTransaction.addToBackStack(Dashboard.class.getName());
        fragmentTransaction.commit();

    }




}

package in.texasreview.gre.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.texasreview.gre.Models.LoginBodyModel;
import in.texasreview.gre.Models.LoginResModel;
import in.texasreview.gre.Models.UserDetails;
import in.texasreview.gre.R;
import in.texasreview.gre.helpers.RetrofitServiceGenerator;
import in.texasreview.gre.interfaces.ManageServices;
import in.texasreview.gre.session.SessionManager;
import in.texasreview.gre.utils.MyProgressBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Login extends AppCompatActivity {

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    @BindView(R.id.tvLoading)
    TextView tvLoading;

    SessionManager sessionManager;
    MyProgressBar myProgressBar;
    String email;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(Login.this);
        myProgressBar = new MyProgressBar(this);
        Log.d("login onCreate", "oncreate");

        if (sessionManager.isLogin()) {

            etEmail.setText(sessionManager.getEmail());
            etPassword.setText(sessionManager.getPassword());
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (validateForm()) {

                    email = etEmail.getText().toString();
                    password = etPassword.getText().toString();
                    login(email, password);

                }
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void login(String mail, String password) {

        tvLoading.setVisibility(View.VISIBLE);
        myProgressBar.show();
        btnLogin.setEnabled(false);

        final LoginBodyModel loginBodyModel = new LoginBodyModel();
        loginBodyModel.setEmail(mail);
        loginBodyModel.setPassword(password);

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<LoginResModel> call = service.createlogin(loginBodyModel);
        call.enqueue(new Callback<LoginResModel>() {
            @Override
            public void onResponse(Call<LoginResModel> call, Response<LoginResModel> response) {

                tvLoading.setVisibility(View.GONE);
                myProgressBar.hide();
                btnLogin.setEnabled(true);

                LoginResModel loginResModel = response.body();

                if (response.code() == 200 && loginResModel.getStatus() == 1001) {

                    getUserDetails(loginResModel.getId());

                } else {

                    Toast.makeText(Login.this, "error" + loginResModel.getStatus(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<LoginResModel> call, Throwable t) {

                displayToast(t.getMessage());
                tvLoading.setVisibility(View.GONE);
                myProgressBar.hide();
                btnLogin.setEnabled(true);

            }
        });
    }

    /*This method for get user details after login service call */
    private void getUserDetails(String Id) {

        myProgressBar.show();
        btnLogin.setEnabled(false);

        ManageServices service = RetrofitServiceGenerator.getRetrofitInstance().create(ManageServices.class);
        Call<UserDetails> call = service.getUserDetails(Id);
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {

                UserDetails userDetails = response.body();

                myProgressBar.hide();
                tvLoading.setVisibility(View.GONE);
                btnLogin.setEnabled(true);

                if (userDetails.getStatus().equalsIgnoreCase("1001")) {

                    sessionManager = new SessionManager(Login.this);
                    sessionManager.createLoginSession(true, userDetails.getEmail(), userDetails.getFullname(), userDetails.getId(), userDetails.getBranch(), userDetails.getActtype(), userDetails.getPhonenumber(), etPassword.getText().toString());
                    gotoDashboard();

                } else if (userDetails.getStatus().equalsIgnoreCase("1002")) {

                    displayToast("Your account is inactive. contact your administrator to activate");
                }

            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {

                myProgressBar.hide();
                tvLoading.setVisibility(View.GONE);
                btnLogin.setEnabled(true);

                displayToast(t.getMessage());


            }
        });
    }

    private boolean validateForm() {
        boolean validform = false;
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (!email.isEmpty()) {
            if (isValidEmail(email)) {
                if (!password.isEmpty()) {
                    validform = true;
                } else {
                    displayToast("Please enter password");
                }
            } else {
                displayToast("Please enter valid email address");
            }
        } else {
            displayToast("Please enter email address");
        }
        return validform;
    }

    public boolean isValidEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,5})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void gotoDashboard() {

        Intent navigation = new Intent(Login.this, NavigationDrawer.class);
        startActivity(navigation);

    }

    private void displayToast(String value) {

        Toast.makeText(Login.this, value, Toast.LENGTH_SHORT).show();
    }


}


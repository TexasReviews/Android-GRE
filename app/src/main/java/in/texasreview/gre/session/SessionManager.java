package in.texasreview.gre.session;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {


    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREF_NAME = "Texas_Review_Gre";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String EMAIL = "email";

    public String getPASSWORD() {
        String password;
        password = pref.getString(PASSWORD, null);
        return password;
    }

    private static final String PASSWORD = "password";
    private static final String MOBILENUM = "mobile_num";

    public String getFULLNAME() {
        String fullName = null;
        fullName = pref.getString(FULLNAME, null);
        return fullName;
    }

    private static final String FULLNAME = "fullName";
    private static final String ACTTYPE = "actType";
    private static final String STATUS = "status";
    private static final String BRANCH = "Branch";
    private static final String USER_ID = "userId";


    private static final String CREATED = "created";
    private static final String TTl = "ttl";




    // Constructor
    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void clearSession(){

        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    public  String getMOBILENUM() {
        String mobileNum;
        mobileNum = pref.getString(MOBILENUM, null);
        return mobileNum;

    }

    public  String getBRANCH() {
        String branch;
        branch = pref.getString(USER_ID, null);
        return branch;

    }

    /**
     * Create login session
     */
    public void createLoginSession(boolean isLogin, String email_id, String full_name, String userId,String branch,String accttype,String mobileNum,String password) {
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.putString(EMAIL, email_id);
        editor.putString(FULLNAME, full_name);
        editor.putString(USER_ID, userId);
        editor.putString(BRANCH, branch);
        editor.putString(ACTTYPE, accttype);
        editor.putString(MOBILENUM, mobileNum);
        editor.putString(PASSWORD, password);
        // commit changes
        editor.commit();

        }

    public  String getUSER_Id() {
        String userId;
        userId = pref.getString(USER_ID, null);
        return userId;
    }


    public String getEmail() {
        String Uid;
        Uid = pref.getString(EMAIL, null);
        return Uid;
    }

    public String getPassword() {
        String pswd;
        pswd = pref.getString(PASSWORD, null);
        return pswd;
    }



    public boolean isLogin() {
        boolean active;
        active = pref.getBoolean(IS_LOGIN, false);
        return active;
    }


}

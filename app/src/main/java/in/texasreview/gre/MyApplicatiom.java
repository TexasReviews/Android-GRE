package in.texasreview.gre;

import android.app.Application;


import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by User on 11-10-2018.
 */

public class MyApplicatiom extends Application {

    private static String DBName = "gre.realm";

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(DBName)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}


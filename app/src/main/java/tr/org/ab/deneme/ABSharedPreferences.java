package tr.org.ab.deneme;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ABSharedPreferences {

    private Context context;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    private static final String PREFERENCES_NAME = "tr.org.ab.deneme";
    private static final int PREFERENCES_MODE = Activity.MODE_PRIVATE;

    public ABSharedPreferences(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(
                PREFERENCES_NAME,
                PREFERENCES_MODE
        );
        editor = preferences.edit();
    }

}










package tr.org.ab.deneme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    private TextView username;
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Log.d("yasamdongusu", "Activity baslatildi.");

        username = (TextView) findViewById(R.id.username);
        mUsername = getIntent().getStringExtra("username");
        username.setText("Selam " + mUsername);

    }

    @Override
    protected void onPause() {
        Log.d("yasamdongusu", "onPause() calisti");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("yasamdongusu", "onResume() calisti");
    }

    @Override
    protected void onDestroy() {
        Log.d("yasamdongusu", "Activity kapatildi.");
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

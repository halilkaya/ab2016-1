package tr.org.ab.deneme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button buttonLogin;
    private Button buttonDataParse;

    private String mUsername;
    private String mPassword;

    private ABSharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = new ABSharedPreferences(MainActivity.this);

        checkLogin();

        init();

        View.OnLongClickListener listener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("ab2016", "uzun basildi");
                return false;
            }
        };

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername = username.getText().toString();
                mPassword = password.getText().toString();
                if (mUsername.equals("ali") &&
                    mPassword.equals("123")) {

                    sp.editor.putBoolean("isLoggedIn", true);
                    sp.editor.commit();

                    openDashboard();
                    finish();
                }
            }
        });

        buttonDataParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dataParseIntent = new Intent(
                        MainActivity.this,
                        DataParse.class
                );
                startActivity(dataParseIntent);
            }
        });

        buttonLogin.setOnLongClickListener(listener);

    }

    private void checkLogin() {
        boolean isLoggedIn = sp.preferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            openDashboard();
            finish();
        }
    }

    private void openDashboard() {
        Intent dashboardIntent = new Intent(
                MainActivity.this,
                Dashboard.class
        );
        dashboardIntent.putExtra("username", mUsername);
        startActivity(dashboardIntent);
    }

    private void init() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonDataParse = (Button) findViewById(R.id.button_data_parse);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Toast.makeText(
                        MainActivity.this,
                        "Settings not found!",
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            case R.id.action_exit:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

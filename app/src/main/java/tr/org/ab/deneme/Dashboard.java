package tr.org.ab.deneme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private TextView username;
    private String mUsername;
    private ListView cities;
    private Button logoutButton;

    private ArrayList<String> cityList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private ABSharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sp = new ABSharedPreferences(Dashboard.this);

        Log.d("yasamdongusu", "Activity baslatildi.");

        logoutButton = (Button) findViewById(R.id.logout_button);
        username = (TextView) findViewById(R.id.username);
        mUsername = getIntent().getStringExtra("username");
        username.setText("Selam " + mUsername);

        cities = (ListView) findViewById(R.id.cities);

        fillInCities();
        adapter = new ArrayAdapter<String>(
                Dashboard.this,
                android.R.layout.simple_list_item_1,
                cityList
        );
        cities.setAdapter(adapter);

        cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        Dashboard.this,
                        "Sectiginiz sehir: " + cityList.get(position),
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        cities.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
                return false;
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    private void logout() {
        sp.editor.remove("isLoggedIn");
        sp.editor.commit();
        Intent mainIntent = new Intent(
                Dashboard.this,
                MainActivity.class
        );
        startActivity(mainIntent);
        finish();
    }

    private void showAlertDialog(final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Dashboard.this);
        dialog.setTitle("Emin misiniz?");
        dialog.setMessage(
                cityList.get(position) + " kaydini silmek istediginize emin misiniz?"
        );
        dialog.setCancelable(false);
        dialog.setPositiveButton(
                "Evet",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCity(position);
                        Toast.makeText(
                                Dashboard.this,
                                "Kayit basariyla silindi!",
                                Toast.LENGTH_SHORT
                        ).show();
                        dialog.dismiss();
                    }
                }
        );
        dialog.setNegativeButton(
                "Hayır",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        dialog.create().show();
    }

    private void deleteCity(int position) {
        cityList.remove(position);
        adapter.notifyDataSetChanged();
    }

    private void fillInCities() {
        cityList.add("Istanbul");
        cityList.add("Ankara");
        cityList.add("Izmir");
        cityList.add("Adana");
        cityList.add("Aydin");
        cityList.add("Istanbul");
        cityList.add("Ankara");
        cityList.add("Izmir");
        cityList.add("Adana");
        cityList.add("Aydin");
        cityList.add("Istanbul");
        cityList.add("Ankara");
        cityList.add("Izmir");
        cityList.add("Adana");
        cityList.add("Aydin");
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

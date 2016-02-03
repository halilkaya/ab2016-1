package tr.org.ab.deneme;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteJSON extends AppCompatActivity {

    private TextView dataPlace;
    private ProgressDialog progressDialog;

    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_json);

        dataPlace = (TextView) findViewById(R.id.data_place);

        progressDialog = new ProgressDialog(RemoteJSON.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Lutfen bekleyiniz...");

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                // Islem baslamadan once
                Log.d(
                        "remoteData",
                        "Veri cekme islemi basliyor!"
                );
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                // Hangi islem yapilacak?
                Log.d(
                        "remoteData",
                        "Veri cekiliyor..."
                );
                data = getDataFromRemote();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // Islem bitti
                Log.d(
                        "remoteData",
                        "Veri cekme islemi tamamlandi!\n" +
                        data
                );
                dataPlace.setText(data);
                progressDialog.dismiss();
            }
        }.execute();

    }

    private String getDataFromRemote() {
        String json = "";
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL("https://api.seatgeek.com/2/events");
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            int data = reader.read();
            while (data != -1) {
                char currentChar = (char) data;
                data = reader.read();
                json += currentChar;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return json;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_remote_json, menu);
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

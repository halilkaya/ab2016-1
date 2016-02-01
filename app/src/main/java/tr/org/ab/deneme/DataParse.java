package tr.org.ab.deneme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DataParse extends AppCompatActivity {

    private ListView liste;
    private ArrayList<DataModel> insanlar = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_parse);

        liste = (ListView) findViewById(R.id.liste);
        parseData();
        getList();

    }

    private void getList() {
        CustomAdapter adapter = new CustomAdapter(
                DataParse.this,
                R.layout.item_row,
                insanlar
        );
        liste.setAdapter(adapter);
    }

    private void parseData() {
        try {

            JSONObject kayitlarObject = new JSONObject(getData());
            JSONArray kayitlar = kayitlarObject.getJSONArray("kayitlar");

            for (int i=0; i < kayitlar.length(); i++) {
                JSONObject kayitObject = kayitlar.getJSONObject(i);
                DataModel kayit = new DataModel(
                        kayitObject.getInt("id"),
                        kayitObject.getString("ad"),
                        kayitObject.getString("soyad"),
                        kayitObject.getString("sehir")
                );
                insanlar.add(kayit);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getData() {
        String json = "";
        try {
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_parse, menu);
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

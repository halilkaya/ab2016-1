package tr.org.ab.deneme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "AB2016";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE people (" +
                        "id integer," +
                        "ad String," +
                        "soyad String," +
                        "sehir String" +
                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS people");
    }

    public boolean insertData(
            int id,
            String ad,
            String soyad,
            String sehir
    ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("id", id);
        row.put("ad", ad);
        row.put("soyad", soyad);
        row.put("sehir", sehir);
        db.insert("people", null, row);
        return true;
    }

    public DataModel getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM people WHERE id=" + String.valueOf(id),
                null
        );
        cursor.moveToFirst();
        return new DataModel(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
    }

}





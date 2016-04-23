package id.its.yaumirev_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Zachary on 4/21/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "amalan.db";
    private static final String TABLE_IBADAH = "ibadah";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TAHAJUD = "tahajud";
    public static final String COLUMN_RAWATIB = "rawatib";
    public static final String COLUMN_DHUHA = "dhuha";
    public static final String COLUMN_PUASA = "puasa";
    public static final String COLUMN_RIYADHOH = "riyadhoh";
    public static final String COLUMN_SHOLAT_JAMAAH = "jamaah";
    private SQLiteDatabase db;


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_AMALAN_TABLE = "CREATE TABLE " +
                TABLE_IBADAH + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TAHAJUD + " INTEGER," +
                COLUMN_RAWATIB + " INTEGER," +
                COLUMN_DHUHA + " INTEGER,"+
                COLUMN_PUASA+ " INTEGER,"+
                COLUMN_RIYADHOH +" INTEGER,"+
                COLUMN_SHOLAT_JAMAAH+" INTEGER"+")";

        db.execSQL(CREATE_AMALAN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_IBADAH);
        onCreate(db);
    }
    
    public Long addNewRow() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TAHAJUD,1);
        values.put(COLUMN_RAWATIB,1);
        values.put(COLUMN_DHUHA,1);
        values.put(COLUMN_PUASA,1);
        values.put(COLUMN_RIYADHOH,1);
        values.put(COLUMN_SHOLAT_JAMAAH,1);

        db = this.getWritableDatabase();
        Long id = db.insert(TABLE_IBADAH, null, values);

//        System.out.println(id);
        db.close();
        return id;
    }

    public void addSomething(Long id,String attrib, String value){
        System.out.println(id+ " "+ attrib + " "+value);
            ContentValues values = new ContentValues();
            values.put(attrib,value);
//        System.out.println(values);
            db = this.getWritableDatabase();
            int x = db.update(TABLE_IBADAH,values,"_id="+id,null);
        Log.i("return value of x", String.valueOf(x));
            db.close();

    }

    public String[] getData(){
        db = getReadableDatabase();
        Cursor dbCursor = db.query(TABLE_IBADAH,null,null,null,null,null,null);
        String[] columnNames = dbCursor.getColumnNames();
        db.close();
//        System.out.println(columnNames.length);
        return columnNames;
    }

    public long getDataCount(){
        db = this.getReadableDatabase();
        long cnt = DatabaseUtils.queryNumEntries(db,TABLE_IBADAH);
        db.close();
        System.out.println(cnt);
        return cnt;
    }


}

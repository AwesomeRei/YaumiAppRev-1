package id.its.yaumirev_1;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Zachary on 4/25/2016.
 */
public class ReadFileJSON {
    private String fileName = null;
    private Context context;
    private String json = null;

    public ReadFileJSON(Context context, String filename){
        this.context = context;
        this.fileName = filename;

    }

    public String loadJSONFromAsset(){
//        System.out.println(context.getAssets());
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}

package devnitish.com.skillquest.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context mycontext;
    private String DB_PATH;

    private static String DB_NAME = "skillquest.db";
    public SQLiteDatabase myDataBase;


    public DatabaseHelper(Context context) {
        super(context,DB_NAME,null,1);
        this.mycontext=context;

        DB_PATH = "/data/data/"+context.getPackageName()+"/databases";

        boolean dbexist = checkdatabase();
        if (dbexist) {
            opendatabase();
        } else {
            createdatabase();
        }
    }

    public SQLiteDatabase getDatabase(){
        return myDataBase;
    }

    public void createdatabase()  {
        boolean dbexist = checkdatabase();
        if(!dbexist) {
//            this.getReadableDatabase();
            try {
                copydatabase();
            } catch(IOException e) {
                Log.e("Error copying database",Log.getStackTraceString(e));
            }
        }
    }

    private boolean checkdatabase() {

        boolean checkdb = false;
        try {
            String myPath = DB_PATH +"/"+ DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch(SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        File file = new File(DB_PATH);
        file.mkdirs();

        // Path to the just created empty db
        String outfilename = DB_PATH +"/"+ DB_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();

        opendatabase();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH +"/"+ DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath,
                null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if(myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
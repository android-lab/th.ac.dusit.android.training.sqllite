package com.example.witoon.myapp08_sqllite1;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class ShowDatabase extends Activity {
    ////////// ตัวแปรในการกดคเมนูค้าง ///////////
    private String[] Countries;
  //  String[] menuArray = {"Edit", "Delete"};
    //////////////////////
    public SQLiteDatabase mDb;
    public MyOpenHelper mHelper;
    public Cursor mCursor;
  //  public static final String TABLE_CONTACT = "contactTABLE";
  //  public static final String ID = "ID";
  //  public static final String CONTACT = "Contact";
  //  public static final String MESSAGE = "Message";
    public ListView listViewdatabase;
    String index_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);

        listViewdatabase = (ListView) findViewById(R.id.listView);

        ///////// ////////// connect to My SQL Lite  ////////// //////////
        mHelper = new MyOpenHelper(this);
        mDb = mHelper.getWritableDatabase();
        mCursor = mDb.rawQuery("SELECT * FROM " + MyOpenHelper.TABLE_CONTACT, null);

         ArrayList<String> dirArray1 = new ArrayList<String>();
          ArrayList<String> dirArray2 = new ArrayList<String>();
          mCursor.moveToFirst();

          while (!mCursor.isAfterLast()) {
                dirArray1.add(mCursor.getString(mCursor.getColumnIndex(MyOpenHelper.NAME)));
                dirArray2.add(mCursor.getString(mCursor.getColumnIndex(MyOpenHelper.SURNAME)));
                mCursor.moveToNext();
                                     }


////////// ////////// ShowData on ListView  ////////// //////////
         listViewdatabase.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, dirArray1));

//// //////////////////// click on ListView   /////////////////

        listViewdatabase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1
                    , int arg2, long arg3) {
               // mCursor.moveToPosition(arg2);
                //  index_id = mCursor.getString(mCursor.getColumnIndex(MyOpenHelper.ID));
                // Intent intent = new Intent(ListContact.this, EditContact.class);
                // intent.putExtra("index_id",index_id);
                // startActivity(intent);
                //  finish();

            }

        });

    }
/////////////////////////////////////////////////////



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_database, menu);
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
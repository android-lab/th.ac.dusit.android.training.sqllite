package com.example.witoon.myapp08_sqllite1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    public Button btnSave;
    public Button btnShow;
    public EditText txtName;
    public EditText txtSurname;
    public String strName, strSurname;
    public MyOpenHelper objMyOpenHelper;
    public SQLiteDatabase objSQLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        objMyOpenHelper = new MyOpenHelper(this);
        objSQLiteDatabase = objMyOpenHelper.getWritableDatabase();
        txtName = (EditText) findViewById(R.id.editText);
        txtSurname = (EditText) findViewById(R.id.editText2);
        btnSave = (Button) findViewById(R.id.button1);
        btnShow = (Button) findViewById(R.id.button2);

//////////////////// Save Data Click /////////////////////////
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                strName = txtName.getText().toString().trim();
                strSurname = txtSurname.getText().toString().trim();
                //// ตรวจสอบว่ากรอกข้อมูลครบหรือไม่ ////
                if (strName.equals("") || strSurname.equals("")) {
                    showAlert();
                } else {
                    showLog();
                    confirmData();
                }
            }

        });


//////////////////// Show Data Click /////////////////////////

        btnShow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                   Intent intent = new Intent(MainActivity.this, ShowDatabase.class);
                  startActivity(intent);
                  //finish();
            }

        });

    }

////////////////// โปรแกรมย่อย ///////////////////////

    private void showAlert() {
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle("แจ้งเตือน");
        objAlert.setMessage("กรุณากรอกข้อมูลให้ครบถ้วน");
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.show();
    }


    private void showLog() {
        Log.d("sms", "phoneNo = " + strName);
        Log.d("sms", "message = " + strSurname);
    }


    private void confirmData() {
        AlertDialog.Builder objAlert = new AlertDialog.Builder(this);
        objAlert.setTitle("คุณต้องการบันทึก ?");
        objAlert.setMessage("ชื่อ : " + strName + "\n" + "นามสกุล : " + strSurname);
        objAlert.setCancelable(false);
        objAlert.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objAlert.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addContact();
            }
        });
        objAlert.show();
    }

    private void addContact() {
        // TODO Auto-generated method stub
        if (txtName.length() > 0 && txtSurname.length() > 0) {
            //เตรียมข้อมูลสำหรับใส่ลงไปในตาราง
            ContentValues values = new ContentValues();
            values.put("name", txtName.getText().toString());
            values.put("surname", txtSurname.getText().toString());
            SQLiteDatabase mDb;
            MyOpenHelper mHelper;
            mHelper = new MyOpenHelper(this);
            mDb = mHelper.getWritableDatabase();

            //ทำการเพิ่มข้อมูลลงไปในตาราง table_contact
            mDb.insert("table_contact", null, values);
            Toast.makeText(this, "บันทึกข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show();
            txtName.setText("");
            txtSurname.setText("");

        }
    }


////////////////////////////////////////////////////////////////////


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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.pe02;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pe02.providers.MyProvider;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAdd(View view) {
        try{
            ContentValues values = new ContentValues();
            values.put(MyProvider.name, ((EditText) findViewById(R.id.txtName)).getText().toString());
            values.put(MyProvider.price, ((EditText) findViewById(R.id.txtPrice)).getText().toString());
            getContentResolver().insert(MyProvider.CONTENT_URI, values);

            Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
        }
        catch(Exception e) {
            Log.e("Add", e.getMessage());
            Toast.makeText(getBaseContext(), "Add Error", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickDelete(View view) {
        try{
            getContentResolver().delete(MyProvider.CONTENT_URI, "id = " + ((EditText) findViewById(R.id.txtId)).getText().toString(), null);
            Toast.makeText(getBaseContext(), "Delete Success !!!", Toast.LENGTH_LONG).show();
        }
        catch(Exception e) {
            Log.e("Delete", e.getMessage());
            Toast.makeText(getBaseContext(), "Delete Error", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickUpdate(View view) {
        try{
//            getContentResolver().delete(MyProvider.CONTENT_URI, "id = " + ((EditText) findViewById(R.id.txtId)).getText().toString(), null);
//            Toast.makeText(getBaseContext(), "Delete Success !!!", Toast.LENGTH_LONG).show();
            ContentValues values = new ContentValues();
            values.put(MyProvider.id, ((EditText) findViewById(R.id.txtId)).getText().toString());
            values.put(MyProvider.name, ((EditText) findViewById(R.id.txtId)).getText().toString());
            values.put(MyProvider.price, ((EditText) findViewById(R.id.txtPrice)).getText().toString());
            getContentResolver().update(MyProvider.CONTENT_URI, values, "id = " + ((EditText) findViewById(R.id.txtId)).getText().toString(),null);
            Toast.makeText(getBaseContext(), "Update Successfully", Toast.LENGTH_LONG).show();
        }
        catch(Exception e) {
            Log.e("Update", e.getMessage());
            Toast.makeText(getBaseContext(), "Update Error", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickShow(View view) {
        try{
            TextView resultView = (TextView) findViewById(R.id.res);
            Cursor cursor = getContentResolver()
                    .query(MyProvider.CONTENT_URI,
                            null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                StringBuilder strBuild = new StringBuilder();
                while (!cursor.isAfterLast()) {
                    strBuild.append("\n" +
                            cursor.getString(cursor.getColumnIndex("id")) + "-" +
                            cursor.getString(cursor.getColumnIndex("name")) + "-" +
                            cursor.getString(cursor.getColumnIndex("price")));
                    cursor.moveToNext();
                }
                resultView.setText(strBuild);
            }
            else {
                resultView.setText("No Records Found");
            }
        }
        catch (Exception e) {
            Log.e("Show", e.getMessage());
            Toast.makeText(getBaseContext(), "Show Error", Toast.LENGTH_LONG).show();
        }
    }
}
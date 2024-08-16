package com.example.newapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class Addevent extends Activity {
    Button dhp;
    EditText en, ev;
    EditText ed, et;
    SQLiteDatabase db;
    Calendar calendar;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);
        en = (EditText) findViewById(R.id.editText1);
        ev = (EditText) findViewById(R.id.editText3);
        ed = (EditText) findViewById(R.id.editText2);
        et = (EditText) findViewById(R.id.editText4);
        dhp = (Button) findViewById(R.id.button1);
        db = openOrCreateDatabase("Event", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists addevent1(name varchar,venue varchar,date varchar,time varchar);");

        calendar = Calendar.getInstance();

        ed.setOnClickListener(new OnClickListener() {
            @SuppressLint("NewApi")
			@Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Addevent.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Set selected date to the EditText
                                calendar.set(year, monthOfYear, dayOfMonth);
                                ed.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        et.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Addevent.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                et.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, true);
                timePickerDialog.show();
            }
        });

        dhp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == dhp) {
                    if (en.getText().toString().trim().length() == 0 ||
                            ev.getText().toString().trim().length() == 0 ||
                            ed.getText().toString().trim().length() == 0 ||
                            et.getText().toString().trim().length() == 0) {
                        Toast.makeText(Addevent.this, "please enter details", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Cursor c = db.rawQuery("select * from addevent1 where name='" + en.getText() + "'", null);
                    if (c.moveToFirst()) {
                        Toast.makeText(Addevent.this, "event already exist", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        db.execSQL("insert into addevent1 values('" + en.getText() + "','" + ev.getText() + "','" + ed.getText() + "','" + et.getText() + "')");
                        Toast.makeText(Addevent.this, "Event added successfully", Toast.LENGTH_LONG).show();
                        clear();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addevent, menu);
        return true;
    }

    public void clear() {
        en.setText("");
        ev.setText("");
        ed.setText("");
        et.setText("");
    }
}

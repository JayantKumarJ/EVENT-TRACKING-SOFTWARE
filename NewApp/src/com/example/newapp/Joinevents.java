package com.example.newapp;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Joinevents extends Activity {
	Button dhp;
	
	EditText en;
	TextView uid;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_joinevents);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		uid=(TextView)findViewById(R.id.textView3);
		uid.setText(globalvariabel.GetUserName().toString());
		en=(EditText)findViewById(R.id.editText1);
		dhp=(Button)findViewById(R.id.button1);
		db=openOrCreateDatabase("Event", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists eventname1(uid varchar,en varchar);");
		dhp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(en.getText().toString().trim().length()==0)
						
				{
					Toast.makeText(Joinevents.this, "please enter details", Toast.LENGTH_LONG).show();
					return;
				}
				Cursor c=db.rawQuery("select * from eventname1 where en='"+en.getText()+"' and uid='"+uid.getText()+"'", null);
				Cursor c1=db.rawQuery("select * from addevent1 where name='"+en.getText()+"'", null);
				if(c1.moveToFirst())
				{
					db.execSQL("insert into eventname1 values('"+uid.getText()+"','"+en.getText()+"')");
					Toast.makeText(Joinevents.this, "Event enrolled successfully", Toast.LENGTH_LONG).show();
					clear();
				}
				
				else 
				{
					Toast.makeText(Joinevents.this, "check the event", Toast.LENGTH_LONG).show();
					return;
				}
				 if(c.moveToFirst())
					
				{
					Toast.makeText(Joinevents.this, "event already placed", Toast.LENGTH_LONG).show();
					return;
				}
				
				
				
			}
			
		});

	        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.joinevents, menu);
		return true;
	}
	public void clear()
	{
		en.setText("");
		
	}

}


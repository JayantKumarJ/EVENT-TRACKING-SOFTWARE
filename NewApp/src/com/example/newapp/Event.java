package com.example.newapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Event extends Activity {
	Button a;
	Button v;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		setContentView(R.layout.activity_event);
		db=openOrCreateDatabase("Event",Context.MODE_PRIVATE,null);
        a=(Button) findViewById(R.id.button1);
        a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {        		
			Intent i = new Intent(Event.this,Addevent.class);
			startActivity(i);
		    }
        });
        v=(Button) findViewById(R.id.button2);
        v.setOnClickListener(new OnClickListener(){
        	@Override
		public void onClick(View v) {  
	    Cursor c=db.rawQuery("SELECT * FROM eventname1",null);
	    if(c.getCount()==0)
	    {
	    	showMessage("Error","No records found");
	    	return;
	    }
	    StringBuffer buffer=new StringBuffer();
	    while(c.moveToNext())
	    {
	    	buffer.append("User id:"+c.getString(0)+"\n");
        	buffer.append("Eventname:"+c.getString(1)+"\n");
        	
	    }
	    showMessage("User Details",buffer.toString());
		}
        	
 });
	}
	    public void showMessage(String title,String message)
	    {
	    	Builder builder =new Builder(this);
	        builder.setCancelable(true);
	        builder.setTitle(title);
	        builder.setMessage(message);
	        builder.show();
	        
			
	    }


	}

        
					        								        		 
					        	
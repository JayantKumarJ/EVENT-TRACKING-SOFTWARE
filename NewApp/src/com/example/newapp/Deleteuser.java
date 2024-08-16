package com.example.newapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Deleteuser extends Activity {
	EditText u;
	Button dhp;
	SQLiteDatabase db;
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deleteuser);
		u=(EditText)findViewById(R.id.editText2);
	    dhp=(Button)findViewById(R.id.button1);
		db=openOrCreateDatabase("Event", Context.MODE_PRIVATE, null);
		dhp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(u.getText().toString().trim().length()==0)
						
				{
					Toast.makeText(Deleteuser.this, "please enter details", Toast.LENGTH_LONG).show();
					return;
				}
				Cursor c=db.rawQuery("SELECT * FROM registration1 WHERE id='"+u.getText()+"'", null);
				if(c.moveToFirst())
	    		{
	    			db.execSQL("DELETE FROM registration1 WHERE id='"+u.getText()+"'");
	    			showMessage("Success", "Record Deleted");
	    		}
	    		else
	    		{
	    			showMessage("Error", "Invalid id");
	    		}
	    		clearText();
				
			}
			});	
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deleteuser, menu);
		return true;
	}
	public void clearText()
	{
		u.setText("");
		
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

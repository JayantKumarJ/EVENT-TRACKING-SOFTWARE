

package com.example.newapp;


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
import android.widget.Toast;

public class UserRegActivity extends Activity {
	Button dhp;
	EditText u,pw,ph,em;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_reg);
		u=(EditText)findViewById(R.id.editText1);
		pw=(EditText)findViewById(R.id.editText2); 
		ph=(EditText)findViewById(R.id.editText4);
		em=(EditText)findViewById(R.id.editText3);
		dhp=(Button)findViewById(R.id.button1);
		db=openOrCreateDatabase("Event", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists registration1(id varchar,password varchar,email varchar,mobile varchar);");
		dhp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(u.getText().toString().trim().length()==0||
						pw.getText().toString().trim().length()==0||
						ph.getText().toString().trim().length()==0||
						em.getText().toString().trim().length()==0)
				{
					Toast.makeText(UserRegActivity.this, "please enter details", Toast.LENGTH_LONG).show();
					return;
				}
				else if(ph.getText().toString().trim().length()!=10)
				{
					Toast.makeText(UserRegActivity.this, "phone number not valid", Toast.LENGTH_LONG).show();
					return;
				}
				Cursor c=db.rawQuery("select * from registration1 where id='"+u.getText()+"'", null);
				if(c.moveToFirst())
				{
					Toast.makeText(UserRegActivity.this, "user already exist", Toast.LENGTH_LONG).show();
					return;
				}
				else
					
				{
					db.execSQL("insert into registration1 values('"+u.getText()+"','"+pw.getText()+"','"+u.getText()+"','"+ph.getText()+"')");
					Toast.makeText(UserRegActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
					clear();
				}
				
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_reg, menu);
		return true;
	}
	public void clear()
	{
		u.setText("");
		pw.setText("");
		ph.setText("");
		em.setText("");
	}

}


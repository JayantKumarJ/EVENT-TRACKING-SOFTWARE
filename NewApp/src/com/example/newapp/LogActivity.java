package com.example.newapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
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

public class LogActivity extends Activity {
	EditText user, pass;
	Button a;
	 String u;
	 String p;
	 SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		setContentView(R.layout.activity_log);
		user = (EditText) findViewById(R.id.editText1);
		pass = (EditText) findViewById(R.id.editText2);
		a=(Button) findViewById(R.id.button1);
		db=openOrCreateDatabase("Event", Context.MODE_PRIVATE, null);
		a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub			
				if(user.getText().toString().trim().length()==0||
						pass.getText().toString().trim().length()==0){
					
					Toast.makeText(LogActivity.this, "PLz enter the fields..!", Toast.LENGTH_LONG).show();
				}else{	 
					 u = user.getText().toString();
					 p = pass.getText().toString();
					 try{
						 db=openOrCreateDatabase("Event",SQLiteDatabase.CREATE_IF_NECESSARY, null);					    
					        }catch(Exception exception){
					            exception.printStackTrace();
					        }
					 try{
					        	 Cursor dr= db.rawQuery("select * from registration1 where id = '"+u+"' and password = '"+p+"' ", null);
					        	// Cursor gr = db.rawQuery("select * from test where id = '"+u+"' and password = '"+p+"' ", null);
					        	if(user.getText().toString().equals("admin")&&pass.getText().toString().equals("admin")){
					        		Toast.makeText(LogActivity.this, "Welcome To admin Home Page "  + u , Toast.LENGTH_LONG).show();
					                Intent i = new Intent(LogActivity.this,Adminhomepage.class);
					            		startActivity(i);
					        	}
					        								        		 
					        	// User Login
					        	 else if(dr.moveToFirst())
					        	 {
					        		 String temp="";					       
							            if (dr != null) {
							            	if(dr.getCount() > 0)
							            	{
							            	//return true;
							            login g=new login();
							            g.execute();
							            		Toast.makeText(LogActivity.this, "Welcome To Userhomepage "  + u , Toast.LENGTH_LONG).show();
							            		globalvariabel.Setusername(user.getText().toString().trim());
							            		globalvariabel.Setusername(pass.getText().toString().trim());
						            		Intent as = new Intent(LogActivity.this,Userhomepage.class);
							            		startActivity(as);
							            	}else
							            	{
							            		 Toast.makeText(LogActivity.this, "Login Fails..!", Toast.LENGTH_LONG).show();
							            	}
							            	}
						        		 }
						            //	return false;
					        }catch(Exception exception){
					            exception.printStackTrace();
					        }
			       }       
						 	
				}
		});
		
	
	
		
	}
	public class login extends AsyncTask<String, String, String>{

		private ProgressDialog pd;

		protected void onPreExecute() {
			super.onPreExecute();
		 pd = new ProgressDialog(LogActivity.this);
		 pd.setTitle("Please Wait");
		 pd.setMessage("Logging....");
		 pd.setMax(200);
		 pd.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	public void clearText()
	{
		user.setText("");
		pass.setText("");
	}
}	


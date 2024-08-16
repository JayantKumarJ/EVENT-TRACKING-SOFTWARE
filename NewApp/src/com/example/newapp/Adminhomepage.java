package com.example.newapp;

//import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
//import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;

public class Adminhomepage extends Activity  {
	Button a;
	Button l,d,dl;
	SQLiteDatabase db;
	

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		setContentView(R.layout.activity_adminhomepage);
		
		d=(Button) findViewById(R.id.button1);

		db=openOrCreateDatabase("Event",Context.MODE_PRIVATE,null);
      
	

      
	        d.setOnClickListener(new OnClickListener(){
	        	@Override
			public void onClick(View v) {  
		    Cursor c=db.rawQuery("SELECT * FROM registration1",null);
		    if(c.getCount()==0)
		    {
		    	showMessage("Error","No records found");
		    	return;
		    }
		    StringBuffer buffer=new StringBuffer();
		    while(c.moveToNext())
		    {
            	buffer.append("Username:"+c.getString(0)+"\n");
            	buffer.append("Email:"+c.getString(2)+"\n");
            	buffer.append("Mobile no:"+c.getString(3)+"\n");
		    }
		    showMessage("User Details",buffer.toString());
			}
	 });

		   
		    a=(Button) findViewById(R.id.button2);
			a.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				Intent in = new Intent(Adminhomepage.this,Event.class);
				startActivity(in);
			}
			    });
			l=(Button) findViewById(R.id.button3);
	        l.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(Adminhomepage.this,MainActivity.class);
					startActivity(ad);
				}
	  	 });
	        dl=(Button) findViewById(R.id.button4);
	        dl.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
		 			// TODO Auto-generated method stub
					Intent ad= new Intent(Adminhomepage.this,Deleteuser.class);
					startActivity(ad);
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
        
					        								        		 
					        	
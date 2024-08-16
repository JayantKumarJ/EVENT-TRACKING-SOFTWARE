package com.example.newapp;




import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Userhomepage extends Activity {

	Button v;
	Button l,j;
	
	TextView uid;
	SQLiteDatabase db;
	
        protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userhomepage);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		uid=(TextView)findViewById(R.id.textView3);
		uid.setText(globalvariabel.GetUserName().toString());
		db=openOrCreateDatabase("Event",Context.MODE_PRIVATE,null);
         v=(Button) findViewById(R.id.button1);
         v.setOnClickListener(new OnClickListener(){
	        	@Override
			public void onClick(View v){
	        
		    Cursor c=db.rawQuery("SELECT * FROM addevent1  ",null);
		    if(c.getCount()==0)
		    {
		    	showMessage("Error","No records found");
		    	return;
		    }
		    StringBuffer buffer=new StringBuffer();
		    while(c.moveToNext())
		    {
            	buffer.append("Event Name:"+c.getString(0)+"\n");
            	buffer.append("Event Venue:"+c.getString(1)+"\n");
            	buffer.append("Event date:"+c.getString(2)+"\n");
            	buffer.append("Event time:"+c.getString(3)+"\n");
		    }
		    showMessage("event Details",buffer.toString());
			}
	 });

		   
		    j=(Button) findViewById(R.id.button2);
			j.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				Intent in = new Intent(Userhomepage.this,Joinevents.class);
				startActivity(in);
			}
			    });
				
        l=(Button) findViewById(R.id.button3);
        l.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(Userhomepage.this,MainActivity.class);
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


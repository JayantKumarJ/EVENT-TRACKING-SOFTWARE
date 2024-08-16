package com.example.newapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button log,gst,usr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		log=(Button)findViewById(R.id.button3);
		log.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(MainActivity.this,LogActivity.class);
				startActivity(a);
				
			}
		});
		gst=(Button)findViewById(R.id.button1);
		gst.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent b=new Intent(MainActivity.this,Guesthomepage.class);
				startActivity(b);
				
			}
		});
		usr=(Button)findViewById(R.id.button2);
		usr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent c=new Intent(MainActivity.this,UserRegActivity.class);
				startActivity(c);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

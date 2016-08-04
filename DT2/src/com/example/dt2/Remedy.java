package com.example.dt2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Remedy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remedy);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_remedy, menu);
		return true;
	}
	
	 public void remPurchases(View view) {
		    // Do something in response to button
		        Intent intent = new Intent(this, Purchases.class);
		        startActivity(intent);
		    }
	 
	 public void singleAnimal(View view) {
		    // Do something in response to button
		        Intent intent = new Intent(this, SingleAnimal.class);
		        startActivity(intent);
		    }
	 
	 public void allAnimal(View view) {
		    // Do something in response to button
		        Intent intent = new Intent(this, AllAnimals.class);
		        startActivity(intent);
		    }

}

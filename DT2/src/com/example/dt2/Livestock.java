package com.example.dt2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Livestock extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_livestock);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_livestock, menu);
		return true;
	}
	
	/**
     * Called when the user clicks the Livestock button
     */
    public void viewAll(View view) {
    // Do something in response to button
        Intent intent = new Intent(this, Livestock.class);
        startActivity(intent);
    }

    /**
     * Called when the user clicks the Add Animal Or Dose button
     */
    public void viewGroup(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Remedy.class);
        startActivity(intent);
   }
    
    /**
     * Called when the user clicks the Livestock button
     */
    public void addAnimal(View view) {
    // Do something in response to button
        Intent intent = new Intent(this, AddAnimal.class);
        startActivity(intent);
    }

    /**
     * Called when the user clicks the Add Animal Or Dose button
     */
    public void filter(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Remedy.class);
        startActivity(intent);
   }
    
    public void addWeight(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Remedy.class);
        startActivity(intent);
   }
    
   

}

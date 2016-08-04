package com.example.dt2;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	/**
     * Called when the user clicks the Livestock button
     */
    public void goToLivestockListScreen(View view) {
    // Do something in response to button
        Intent intent = new Intent(this, Livestock.class);
        startActivity(intent);
    }

    /**
     * Called when the user clicks the Add Animal Or Dose button
     */
    public void goToRemedy(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Remedy.class);
        startActivity(intent);
   }

}

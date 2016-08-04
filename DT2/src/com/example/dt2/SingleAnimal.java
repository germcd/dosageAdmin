package com.example.dt2;

import java.util.ArrayList;
import java.util.List; 



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SingleAnimal extends Activity {
	
	
	SQLiteDatabase db = null;
	
	DatePicker dp;
	private Spinner spinner1;
	private Spinner spinner2;
    //private Button button1;
    
    TextView quantity;
    TextView administeredBy;
    TextView prescribedBy;
    
    List<String> wlist = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_animal);
		
		dp = (DatePicker) findViewById(R.id.datePicker1);
		
		
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		List<String> list = new ArrayList<String>();
		
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		List<String> list2 = new ArrayList<String>();
		
		quantity = (EditText) findViewById(R.id.editText10);
		administeredBy = (EditText) findViewById(R.id.editText3);
		
		
		
		String name = "/data/data/com.example.dt2/Dosage.sqlite";
		try{
			db = SQLiteDatabase.openDatabase(name, null, SQLiteDatabase.CREATE_IF_NECESSARY);
			Log.i("My Debug Info", "Database is now open");
		}
		catch(SQLiteException e)
		{
			Log.i("My Debug Info", "Database cannot open");
		}
		
		// query the database
		String [] columns = {"ID", "DOB"};
		
		Cursor c = db.query("Animals", columns, null, null, null, null, null);
		
		//Cursor c  = db.rawQuery("SELECT * FROM players;", null);
		
		int totalRecords = c.getCount();
		Log.i("My Debug Info", "Number of records " + c.getCount());
		
		 c.moveToFirst();
		 String val = c.getString(c.getColumnIndexOrThrow("ID"));
		 list.add(val);
		 while(c.moveToNext()){
		 val =  c.getString(c.getColumnIndexOrThrow("ID"));
		 list.add(val);
		 }
		 
		 c.close();
		 
		 String [] columns2 = {"Name", "BatchNo", "Withdrawl"};
			
		 Cursor c2 = db.query("RemPurchases", columns2, null, null, null, null, null);
		 
		 
		 int totalRecords2 = c2.getCount();
			Log.i("My Debug Info", "Number of purchase records " + c2.getCount());
			
			 c2.moveToFirst();
			 val = c2.getString(c2.getColumnIndexOrThrow("Name"));
			 list2.add(val);
			 wlist.add(c2.getString(c2.getColumnIndexOrThrow("Withdrawl")));
			 while(c2.moveToNext())
			 {
				 val =  c2.getString(c2.getColumnIndexOrThrow("Name"));
				 list2.add(val);
				 wlist.add(c2.getString(c2.getColumnIndexOrThrow("Withdrawl")));
			 }
			 
			 
			 c2.close();
		 
		 ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
         (this, android.R.layout.simple_spinner_item,list);
          
		dataAdapter.setDropDownViewResource
		         (android.R.layout.simple_spinner_dropdown_item);
		
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item,list2);
         
		dataAdapter2.setDropDownViewResource
		         (android.R.layout.simple_spinner_dropdown_item);
		          
		spinner1.setAdapter(dataAdapter);
		
		spinner2.setAdapter(dataAdapter2);
		
		// Spinner item selection Listener  
        //addListenerOnSpinnerItemSelection();
         
        // Button click Listener 
        //addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_single_animal, menu);
		return true;
	}
	
	public void clickSubmit(View view) {
		
		String name = "/data/data/com.example.dt2/Dosage.sqlite";
		try{
			db = SQLiteDatabase.openDatabase(name, null, SQLiteDatabase.CREATE_IF_NECESSARY);
			Log.i("My Debug Info", "Database is now open");
		}
		catch(SQLiteException e)
		{
			Log.i("My Debug Info", "Database cannot open");
		}
		
		String [] columns4 = {"RemName", "AID"};
		Cursor c4 = db.query("RemUsage", columns4, null, null, null, null, null);
		
		
		Log.i("My Debug Info", "Number of purchase records " + c4.getCount());
		
		 Cursor c3 = db.rawQuery("INSERT INTO RemUsage(Date, RemName, Quantity,  WithdrawlDate, AID, AdministeredBy ) " +
					"VALUES (date(julianday('" + dp.getYear() + "-" + (dp.getMonth() +1 ) + "-" +  dp.getDayOfMonth() + "')), '" +
					String.valueOf(spinner2.getSelectedItem()) +  "', " + String.valueOf(quantity.getText()) +
					" , date(julianday('" + dp.getYear() + "-" + (dp.getMonth() + 1) + "-" +  dp.getDayOfMonth() + "'), '+" + wlist.get(spinner2.getSelectedItemPosition()) + " day'), '" 
					+ String.valueOf(spinner1.getSelectedItem()) + "', '" + String.valueOf(administeredBy.getText()) +"' ) ;", null);
			
			 
				
			 c4 = db.query("RemUsage", columns4, null, null, null, null, null);
			
			int totalRecords3 = c3.getCount();
			Log.i("My Debug Info", "Number of purchase records " + c4.getCount());
		
			
			Intent intent = new Intent(this, Remedy.class);
	        startActivity(intent);
		
		
		
	}

}

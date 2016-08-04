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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Variable extends Activity {
	
	
SQLiteDatabase db = null;
	
	DatePicker dp;
	
	private Spinner spinner2;
    //private Button button1;
    
    TextView quantity;
    TextView administeredBy;
    TextView prescribedBy;
    
    List<String> wlist = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_variable);
		
		
		dp = (DatePicker) findViewById(R.id.datePicker1);
		
		
		
		
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		List<String> list2 = new ArrayList<String>();
		
		quantity = (EditText) findViewById(R.id.editText3);
		administeredBy = (EditText) findViewById(R.id.editText4);
		
		
		String name = "/data/data/com.example.dt2/Dosage.sqlite";
		try{
			db = SQLiteDatabase.openDatabase(name, null, SQLiteDatabase.CREATE_IF_NECESSARY);
			Log.i("My Debug Info", "Database is now open");
		}
		catch(SQLiteException e)
		{
			Log.i("My Debug Info", "Database cannot open");
		}
		
		
		String [] columns2 = {"Name", "BatchNo", "Withdrawl"};
		
		 Cursor c2 = db.query("RemPurchases", columns2, null, null, null, null, null);
		 
		 
		 int totalRecords2 = c2.getCount();
			Log.i("My Debug Info", "Number of purchase records " + c2.getCount());
			
			 c2.moveToFirst();
			 String val = c2.getString(c2.getColumnIndexOrThrow("Name"));
			 list2.add(val);
			 wlist.add(c2.getString(c2.getColumnIndexOrThrow("Withdrawl")));
			 while(c2.moveToNext())
			 {
				 val =  c2.getString(c2.getColumnIndexOrThrow("Name"));
				 list2.add(val);
				 wlist.add(c2.getString(c2.getColumnIndexOrThrow("Withdrawl")));
			 }
			 
			 
			 c2.close();
			 
			 ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>
		        (this, android.R.layout.simple_spinner_item,list2);
		         
				dataAdapter2.setDropDownViewResource
				         (android.R.layout.simple_spinner_dropdown_item);
				          
				
				
				spinner2.setAdapter(dataAdapter2);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_variable, menu);
		return true;
	}
	
	
	public void clickSubmit(View view) {
		 //c2.close();
			/*
			String name = "/data/data/com.example.dt2/Dosage.sqlite";
			try{
				db = SQLiteDatabase.openDatabase(name, null, SQLiteDatabase.CREATE_IF_NECESSARY);
				Log.i("My Debug Info", "Database is now open");
			}
			catch(SQLiteException e)
			{
				Log.i("My Debug Info", "Database cannot open");
			}
			*/
			
			String [] columns4 = {"RemName", "AID"};
			Cursor c4 = db.query("RemUsage", columns4, null, null, null, null, null);
			Log.i("My Debug Info", "Number of purchase records " + c4.getCount());
			
			c4.close();
			
			Log.i("My Debug Info", "Number of purchase records " + c4.getCount());
			
			 String query = "INSERT INTO RemUsage(Date, RemName, Quantity,  WithdrawlDate, AID, AdministeredBy ) " +
						" SELECT date(julianday('" + dp.getYear() + "-" + (dp.getMonth() +1 ) + "-" +  dp.getDayOfMonth() + "')), '" +
						String.valueOf(spinner2.getSelectedItem()) +  "', " + String.valueOf(quantity.getText()) +
						" , date(julianday('" + dp.getYear() + "-" + (dp.getMonth() + 1) + "-" +  dp.getDayOfMonth() + "'), '+" + wlist.get(spinner2.getSelectedItemPosition()) + " day'), " 
						+ " ID ,  '" + String.valueOf(administeredBy.getText()) +"' FROM Animals  ;";
			 
			 System.out.println(query);
			 Cursor c3 =  db.rawQuery(query, null);
				
				 //c3.close();
					
				 c4 = db.query("RemUsage", columns4, null, null, null, null, null);
				
				int totalRecords3 = c3.getCount();
				Log.i("My Debug Info", "Number of purchase records " + c4.getCount());
				
				c4.close();
				
				db.close();
				
				Intent intent = new Intent(this, Remedy.class);
		        startActivity(intent);
			
			
			
		}
	
	

}

package com.example.dt2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class Purchases extends Activity {
	
	SQLiteDatabase db = null;
	
	private DatePicker dp;
	
	EditText remName;
	EditText quantity;
	EditText suppliedBy;
	EditText batchNo;
	EditText withdrawl;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchases);
		
		dp = (DatePicker) findViewById(R.id.datePicker1);
		remName = (EditText) findViewById(R.id.editText1);
		quantity = (EditText) findViewById(R.id.editText2);
		suppliedBy = (EditText) findViewById(R.id.editText3);
		batchNo  = (EditText) findViewById(R.id.editText4);
		withdrawl  = (EditText) findViewById(R.id.editText5);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_purchases, menu);
		return true;
	}
	
	 public void clickSubmit(View view) {
	        // Do something in response to button
	        //Intent intent = new Intent(this, Remedy.class);
	        //startActivity(intent);
		 /*
		 	Date date1= (Date) new Date
				   (dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
				try {
					date1  = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(date1.toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
		 
		 Log.i("My Debug Info", "Number of purchase records " + dp.getYear());
				
				Toast.makeText(getApplicationContext(),
		                "On Button Click : " + dp.getYear()
		                + "-" + (dp.getMonth() + 1) + "-" + dp.getDayOfMonth(),
		                
		                Toast.LENGTH_LONG).show();
				
		
				String name = "/data/data/com.example.dt2/Dosage.sqlite";
				try{
					db = SQLiteDatabase.openDatabase(name, null, SQLiteDatabase.CREATE_IF_NECESSARY);
					Log.i("My Debug Info", "Database is now open");
				}
				catch(SQLiteException e)
				{
					Log.i("My Debug Info", "Database cannot open");
				}
				
				
				ContentValues insertValues = new ContentValues();
				insertValues.put("Name", String.valueOf(remName.getText()));
				insertValues.put("Quantity",  String.valueOf(quantity.getText()));
				insertValues.put("PDate", dp.getYear() + "-" + dp.getMonth() + "-" +  dp.getDayOfMonth());
				insertValues.put("SuppliedBy", String.valueOf(suppliedBy.getText()));
				insertValues.put("BatchNo", String.valueOf(batchNo.getText()));
				insertValues.put("withdrawl", Integer.parseInt(String.valueOf(withdrawl.getText())));
				db.insert("RemPurchases", null, insertValues);
				
				
				
				
				/*
				
				db.rawQuery("INSERT INTO RemPurchases (Name, PDate, Quantity, SuppliedBy, BatchNo, Withdrawl) Values('" + String.valueOf(remName.getText()) + "','" + 
						dp.getYear() + "-" + dp.getMonth() + "-" +  dp.getDayOfMonth() + "', '" +  String.valueOf(quantity.getText()) +  "','" 
						+  String.valueOf(suppliedBy.getText()) + "','" +  String.valueOf(batchNo.getText()) + "'," +  String.valueOf(withdrawl.getText()) + ";", null);
				
				*/
				
				 String [] columns2 = {"Name", "BatchNo", "Withdrawl"};
					
				 Cursor c2 = db.query("RemPurchases", columns2, null, null, null, null, null);
				 
				 
				 int totalRecords2 = c2.getCount();
					Log.i("My Debug Info", "Number of purchase records " + c2.getCount());
				c2.close();
				
				
				Intent intent = new Intent(this, Remedy.class);
		        startActivity(intent);
	   } 
	 
	 

}

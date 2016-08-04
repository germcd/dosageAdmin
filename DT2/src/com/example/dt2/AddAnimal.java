package com.example.dt2;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddAnimal extends Activity {
	
	SQLiteDatabase db = null;
	
	private DatePicker dp;
	
	EditText IDtext;
	EditText aliasText;
	EditText sText;
	EditText breedText;
	
	Spinner spinner1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_animal);
		
		
		
		dp = (DatePicker) findViewById(R.id.datePicker1);
		IDtext = (EditText) findViewById(R.id.editText1);
		aliasText = (EditText) findViewById(R.id.editText2);
		sText = (EditText) findViewById(R.id.editText3);
		breedText = (EditText) findViewById(R.id.editText4);
		
		
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		List<String> list = new ArrayList<String>();
		
		
		list = Arrays.asList(new String[]{"AA", "AAX", "CH", "CHX", "FR", "FRX", "HE", "HEX", "KE", "KEX", "LM", "LMX"});
		
		
		 ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
         (this, android.R.layout.simple_spinner_item,list);
          
		dataAdapter.setDropDownViewResource
		         (android.R.layout.simple_spinner_dropdown_item);
		
		
		spinner1.setAdapter(dataAdapter);
			
			
			
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_animal, menu);
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
		/*
		Toast.makeText(getApplicationContext(),
                "On Button Click : " + 
                "\n" + String.valueOf(spinner1.getSelectedItem())
                 + "\n" + String.valueOf(spinner2.getSelectedItem()) ,
                 
                Toast.LENGTH_LONG).show();
                */
		
		
		String name = "/data/data/com.example.dt2/Dosage.sqlite";
		try{
			db = SQLiteDatabase.openDatabase(name, null, SQLiteDatabase.CREATE_IF_NECESSARY);
			Log.i("My Debug Info", "Database is now open");
		}
		catch(SQLiteException e)
		{
			Log.i("My Debug Info", "Database cannot open");
		}
		
		
		String [] columns = {"ID", "DOB"};
		
		Cursor c = db.query("Animals", columns, null, null, null, null, null);
		
		//Cursor c  = db.rawQuery("SELECT * FROM players;", null);
		
		Log.i("My Debug Info", "Number of records " + String.valueOf(IDtext.getText()));
		
		int totalRecords = c.getCount();
		Log.i("My Debug Info", "Number of records " + c.getCount());
		
		ContentValues insertValues = new ContentValues();
		insertValues.put("ID", String.valueOf(IDtext.getText()));
		insertValues.put("Alias",  String.valueOf(aliasText.getText()));
		insertValues.put("DOB", dp.getYear() + "-" + dp.getMonth() + "-" +  dp.getDayOfMonth());
		insertValues.put("Sex", String.valueOf(sText.getText()));
		insertValues.put("Breed", String.valueOf(spinner1.getSelectedItem()));
		//insertValues.put("AdministeredBy", "me");
		db.insert("Animals", null, insertValues);
		
        /*
        Cursor c3 = db.rawQuery("INSERT INTO Animals(ID, Alias, DOB,  Sex, Breed ) " +
				"VALUES ('" + IDtext.getText() + "', '" + aliasText.getText() +  "', '" +  dp.getYear() + "-" + dp.getMonth() + "-" +  dp.getDayOfMonth()+ "' , '" + sText.getText()
						+ "', '" + breedText.getText() + "');", null);
        */
		
        //String [] columns = {"ID", "DOB"};
		
		 Cursor c4 = db.query("Animals", columns, null, null, null, null, null);
		
		//Cursor c  = db.rawQuery("SELECT * FROM players;", null);
		
		//int totalRecords = c.getCount();
		Log.i("My Debug Info", "Number of records " + c4.getCount());
		//Log.i("My Debug Info", "Number of purchase records " + c4.getCount());
		
		c4.close();
		
		Intent intent = new Intent(this, Livestock.class);
        startActivity(intent);
        
	 
	
   }

}

package com.assignment1_app2;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.*;

public class MainActivity extends Activity {

	public Color foreground;
	public Color background;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<String> formats = new ArrayList<String>();
		formats.add("Choose Format");
		formats.add("RGB");
		formats.add("Hex");
		formats.add("Decimal");
		formats.add("Decimal Reversed");
		formats.add("Hexadecimal");
		Spinner mainlist = (Spinner)findViewById(R.id.spinner1);		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, formats);		
		mainlist.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		RelativeLayout cscreen = (RelativeLayout)findViewById(R.id.mainlayout);
		cscreen.setBackgroundColor(Color.BLACK);		
		TextView dummytxt = (TextView)findViewById(R.id.textView1);
		RadioButton fground = (RadioButton)findViewById(R.id.radioButton2);		
		RadioButton bground = (RadioButton)findViewById(R.id.radioButton1);
		Button invertbtn = (Button)findViewById(R.id.button2);
		Button refreshbtn = (Button)findViewById(R.id.button1);
		EditText firstTBox  = (EditText)findViewById(R.id.EditText02);
		EditText secondTBox  = (EditText)findViewById(R.id.EditText01);
		EditText thirdTBox  = (EditText)findViewById(R.id.EditText03);
		LinearLayout output = (LinearLayout)findViewById(R.id.linearLayout1);
		for (int i = 0; i < output.getChildCount(); i++) {
			TextView t = (TextView) output.getChildAt(i);
			t.setTextColor(Color.WHITE);
		}
		
		fground.setTextColor(Color.WHITE);
		bground.setTextColor(Color.WHITE);
		firstTBox.setTextColor(Color.WHITE);
		secondTBox.setTextColor(Color.WHITE);
		thirdTBox.setTextColor(Color.WHITE);		
		secondTBox.setVisibility(View.INVISIBLE);
		thirdTBox.setVisibility(View.INVISIBLE);
		dummytxt.setTextColor(Color.WHITE);
		invertbtn.setBackgroundColor(Color.WHITE);
		refreshbtn.setBackgroundColor(Color.WHITE);
		invertbtn.setTextColor(Color.BLACK);
		refreshbtn.setTextColor(Color.BLACK);
		getMenuInflater().inflate(R.menu.main, menu);		
		return true;
	}

}

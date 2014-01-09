package com.assignment1_app2;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.*;

public class MainActivity extends Activity implements OnClickListener, OnItemSelectedListener {

	public Colour foreground;
	public Colour background;
	Spinner mainlist;
	RelativeLayout cscreen;
	TextView dummytxt;
	RadioButton fground;
	RadioButton bground;
	EditText firstTBox;
	EditText secondTBox;
	EditText thirdTBox;
	LinearLayout output;
	Button invertbtn;
	Button refreshbtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		foreground = new Colour("FFFFFF");
		background = new Colour(0,0,0);
		refreshBG(Color.rgb(background.red, background.green, background.blue));
		refresh(Color.rgb(foreground.red, foreground.green, foreground.blue));
		ChangeValues();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.		
		getMenuInflater().inflate(R.menu.main, menu);		
		return true;
	}

	@Override
	public void onClick(View view1) {
		// TODO Auto-generated method stub
		//get values
		//1. foreground or background
		boolean isBG = bground.isChecked();	
		String format = mainlist.getSelectedItem().toString();
		if (view1.getId() == refreshbtn.getId()) {
			try {
				boolean canChange = Colours(format, isBG);
				if (canChange && isBG) {
					//background colour change
					refreshBG(Color.rgb(background.red, background.green, background.blue));					
					view1.invalidate();
				}
				else if (canChange && !isBG) {
					//foreground colour change
					refresh(Color.rgb(foreground.red, foreground.green, foreground.blue));
					ChangeValues();
					view1.invalidate();
				}				
				
			}
			catch (Exception e) {
				
			}
			
		}		
		else {
			try {
				if (isBG) {
					background = background.invert();
					refreshBG(Color.rgb(background.red, background.green, background.blue));
				}
				else
					foreground = foreground.invert();
					refresh(Color.rgb(foreground.red, foreground.green, foreground.blue));
					ChangeValues();
			}
			catch (Exception e) {
			
			}
		}
	}

	public void refresh(int foregroundColour) {
		ArrayList<String> formats = new ArrayList<String>();
		formats.add("Choose Format");
		formats.add("RGB");
		formats.add("Decimal");		
		formats.add("Hexadecimal");
		mainlist = (Spinner)findViewById(R.id.spinner1);
		mainlist.setOnItemSelectedListener(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, formats);		
		mainlist.setAdapter(adapter);
		dummytxt = (TextView)findViewById(R.id.textView1);
		fground = (RadioButton)findViewById(R.id.radioButton2);		
		bground = (RadioButton)findViewById(R.id.radioButton1);
		invertbtn = (Button)findViewById(R.id.button2);
		refreshbtn = (Button)findViewById(R.id.button1);
		
		invertbtn.setOnClickListener(this);
		refreshbtn.setOnClickListener(this);
		firstTBox  = (EditText)findViewById(R.id.EditText02);
		secondTBox  = (EditText)findViewById(R.id.EditText01);
		thirdTBox  = (EditText)findViewById(R.id.EditText03);
		LinearLayout output = (LinearLayout)findViewById(R.id.linearLayout1);
		for (int i = 0; i < output.getChildCount(); i++) {
			TextView t = (TextView) output.getChildAt(i);
			t.setTextColor(foregroundColour);
		}
		
		fground.setTextColor(foregroundColour);
		bground.setTextColor(foregroundColour);
		firstTBox.setTextColor(foregroundColour);
		secondTBox.setTextColor(foregroundColour);
		thirdTBox.setTextColor(foregroundColour);
		firstTBox.setVisibility(View.INVISIBLE);
		secondTBox.setVisibility(View.INVISIBLE);
		thirdTBox.setVisibility(View.INVISIBLE);
		dummytxt.setTextColor(foregroundColour);
		invertbtn.setBackgroundColor(foregroundColour);
		refreshbtn.setBackgroundColor(foregroundColour);
		invertbtn.setTextColor(Color.BLACK);
		refreshbtn.setTextColor(Color.BLACK);
	}
	
	public void refreshBG(int backgroundColour) {
		cscreen = (RelativeLayout)findViewById(R.id.mainlayout);
		cscreen.setBackgroundColor(backgroundColour);		
		
	}

	@Override
	public void onItemSelected(AdapterView<?> v1, View v2, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int format = v1.getSelectedItemPosition();
		if (format > 1) {
			secondTBox.setVisibility(View.INVISIBLE);
			thirdTBox.setVisibility(View.INVISIBLE);
			firstTBox.setVisibility(View.VISIBLE);
		}
		else if (format == 1) {
			secondTBox.setVisibility(View.VISIBLE);
			thirdTBox.setVisibility(View.VISIBLE);
			firstTBox.setVisibility(View.VISIBLE);
		}
		firstTBox.setText("");
		secondTBox.setText("");
		thirdTBox.setText("");
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//text[] an array of strings, text[0] - format, 
	public boolean Colours(String format1, boolean isBG) throws Exception { 	
		if (format1.equals("RGB")) {
			int red = Integer.parseInt(firstTBox.getText().toString());
			int green = Integer.parseInt(secondTBox.getText().toString());
			int blue = Integer.parseInt(thirdTBox.getText().toString());
			if (isBG)
				background = new Colour(red, green, blue);
			else
				foreground = new Colour(red, green, blue);
			return true;
		}
		else if (format1.equals("Hexadecimal")) {
			String hexcol = firstTBox.getText().toString();
			if (isBG)
				background = new Colour(hexcol);
			else 
				foreground = new Colour(hexcol);
			return true;
		}
		else if (format1.equals("Decimal")) {
			int decimal = Integer.parseInt(firstTBox.getText().toString());
			if (isBG)
				background = new Colour(decimal);
			else 
				foreground = new Colour(decimal);
			return true;
		}		
		else
			return false;
	}
	
	public void ChangeValues() {
		TextView redT = (TextView)findViewById(R.id.red);
		TextView greenT = (TextView)findViewById(R.id.green);
		TextView blueT = (TextView)findViewById(R.id.blue);		
		TextView hexT = (TextView)findViewById(R.id.hex);
		TextView decT = (TextView)findViewById(R.id.dec);
		TextView reversedT = (TextView)findViewById(R.id.reverse);
		redT.setText("Red: " + foreground.red + "");
		greenT.setText("Green: " + foreground.green + "");
		blueT.setText("Blue: " + foreground.blue + "");
		hexT.setText("Hex: " + String.valueOf(foreground.hexadecimal).toUpperCase() + "");
		decT.setText("Decimal: " + foreground.decimal + "");
		reversedT.setText("Dec Reversed: " + foreground.decimal_reversed + "");
	}
	
    public boolean onOptionsItemSelected(MenuItem item) {

		foreground = new Colour("FFFFFF");
		background = new Colour(0,0,0);        	
		refreshBG(Color.rgb(background.red, background.green, background.blue));
		refresh(Color.rgb(foreground.red, foreground.green, foreground.blue));
		super.onRestart();
        return true;
 
    }
}

package com.assignment1_app2;


public class Colour {

	/**
	 * @param args
	 */
	public int red;
	public int green;
	public int blue;
	public int decimal;
	public int decimal_reversed; 
	public String hexadecimal;
	
	//hexadecimal = 0 
	public Colour(String colour) {	
		this.decimal = (int) Long.parseLong(colour, 16);
		this.hexadecimal = Integer.toHexString(this.decimal);
		char hexarray[] = colour.toCharArray();
		this.red = (getInt(hexarray[0]) * 16) + getInt(hexarray[1]);
		this.green = (getInt(hexarray[2]) * 16) + getInt(hexarray[3]);
		this.blue = (getInt(hexarray[4]) * 16) + getInt(hexarray[5]);		
		reverse();
	}
	
	//decimal
	public Colour(int colour) {
		this.decimal = colour;
		this.red = decimal / 65536;
		this.green = (decimal - red * 65536) / 256;
		this.blue = (decimal - red * 65536 - green * 256);	
		this.hexadecimal = "#" + getHexValues(red) + "" + getHexValues(green) + "" + getHexValues(blue);
		reverse();
	}
	
	//RGB
	public Colour(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.decimal = (int) ((red *  Math.pow(256, 2)) + (green *  Math.pow(256, 1)) + (blue *  Math.pow(256, 0)));
		this.hexadecimal = "#" + getHexValues(red) + "" + getHexValues(green) + "" + getHexValues(blue);
		reverse();
	}
	
	public Colour invert() {
		return new Colour(this.decimal_reversed);	
	}
	
	char[] setList() {
		char hexchars[] = new char[16];		
		for (int i = 0; i <= 15; i++) {
			if (i < 10) {
				char[] a = Integer.toString(i).toCharArray();
				hexchars[i] = a[0];
			}
			else {
				hexchars[i] = (char)(i + 55);
			}			
		}
		return hexchars;
	}
	
	int getInt(char c) {
		char hexes[] = setList();
		for (int i = 0; i < hexes.length; i++) {
			if (c == hexes[i]) { 				
				return i;
			}
		}
		return '0';	
	}
	
	char getChar(int a) {
		char hexes[] = setList();
		return hexes[a];	
	}
	
	String getHexValues(int v) {
		char a = getChar(v / 16);		
		char b = getChar(v % 16);
		return (a + "" + b);
	}	

	void reverse() {
		if (this.decimal == 0)
			this.decimal_reversed = 16777215;
		else if (this.decimal == 16777215)
			this.decimal_reversed = 0;
		else 
			this.decimal_reversed = (int) ((red *  Math.pow(256, 0)) + (green *  Math.pow(256, 1)) + (blue *  Math.pow(256, 2)));
	}
}

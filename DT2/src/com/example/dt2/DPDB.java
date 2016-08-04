package com.example.dt2;

public class DPDB {
	
	public String dbDate(int x){
		String d;
		if(String.valueOf(x).length() == 1){
			d = "0" + x;
		}
		else d = "" + x;
		return d;
	}

}

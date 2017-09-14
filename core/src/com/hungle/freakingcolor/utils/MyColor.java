package com.hungle.freakingcolor.utils;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class MyColor {
	public static TwoColor color1 = new TwoColor(parseColor("d62929"), parseColor("ad0e0e"));//1
	public static TwoColor color2 = new TwoColor(parseColor("262d5c"), parseColor("3d4aa1"));//2
	public static TwoColor color3 = new TwoColor(parseColor("45d590"), parseColor("62ae8a"));//3
	public static TwoColor color4 = new TwoColor(parseColor("dca114"), parseColor("ffb400"));//4
	public static TwoColor color5 = new TwoColor(parseColor("5cb4b3"), parseColor("3aa3a2"));//5
	public static TwoColor color6 = new TwoColor(parseColor("3e79bb"), parseColor("38679c"));//6
	public static TwoColor color7 = new TwoColor(parseColor("5b7fa4"), parseColor("4e7db2"));//7
	public static TwoColor color8 = new TwoColor(parseColor("c8a085"), parseColor("4dadb0"));
	public static TwoColor color9 = new TwoColor(parseColor("f11414"), parseColor("4dadb0"));
	public static Color xam = 
			new Color(241f / 255, 241f / 255, 241f / 255, 1f);
	public static Color white = 
			new Color(1, 1, 1, 1);
	public static Color blue = 
			new Color(103f / 255, 183f / 255, 240f / 255, 1f);
	public static Color red = parseColor("ff0000");

	public static TwoColor getPlayColor() {
		int rd = new Random().nextInt(9) + 1;
		if (rd == 1)
			return color1;
		if (rd == 2)
			return color2;
		if (rd == 3)
			return color3;
		if (rd == 4)
			return color4;
		if (rd == 5)
			return color5;
		if (rd == 6)
			return color6;
		if (rd == 7)
			return color7;
		if (rd == 8)
			return color8;
		if (rd == 9)
			return color9;
		return color1;
	}
	
	public static TwoColor getItemColor1(){
		int rd = new Random().nextInt(9) + 1;
		if (rd == 1)
			return color1;
		if (rd == 2)
			return color2;
		if (rd == 3)
			return color3;
		if (rd == 4)
			return color4;
		if (rd == 5)
			return color5;
		if (rd == 6)
			return color6;
		if (rd == 7)
			return color7;
		if (rd == 8)
			return color8;
		if (rd == 9)
			return color9;
		return color1;
	}
	
	public static Color parseColor(String hex) {
		String hex1 = hex;
		if (hex1.indexOf("#") != -1) {
			hex1 = hex1.substring(1);
		}
		Color color = Color.valueOf(hex1);
		//color.a = alpha;
		return color;
	}
}

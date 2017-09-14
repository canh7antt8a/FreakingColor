package com.hungle.freakingcolor.utils;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

public class TwoColor {
	
	ArrayList<Color> twocolor = new ArrayList<Color>();
	public TwoColor(Color color1,Color color2){
		twocolor.add(color1);
		twocolor.add(color2);
	}
	
	public Color getColor(int index){
		return twocolor.get(index-1);
	}
	
}

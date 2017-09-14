package com.hungle.freakingcolor.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class MyPrefs {
	private static final String PREFS_NAME = "FreakingColor";
	private static final String SOUND = "sound";
	private static final String MUSIC = "music";
	private static final String BEST = "bestscore";
	private static final String TIME = "time";
	private static final String DEMO = "demo";
	private static final String NEWGAME = "newgame";

	public static Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);

	public static void setDemo(boolean demo) {
		prefs.putBoolean(DEMO, demo);
		prefs.flush();
	}
	
	public static void disableAnimNewGame(boolean newgame){
		prefs.putBoolean(NEWGAME, newgame);
		prefs.flush();
	}
	
	public static boolean getdisableAnimNewGame(){
		return prefs.getBoolean(NEWGAME);
	}

	public static boolean getDemo() {
		return prefs.getBoolean(DEMO, false);
	}

	public static void setMusic(boolean isMusic) {
		prefs.putBoolean(MUSIC, isMusic);
		prefs.flush();
	}

	public static boolean getMusic() {
		return prefs.getBoolean(MUSIC, true);
	}

	public static void setSound(boolean isSound) {
		prefs.putBoolean(SOUND, isSound);
		prefs.flush();
	}

	public static boolean getSound() {
		return prefs.getBoolean(SOUND, true);
	}

	public static void setBestScore(int score) {
		prefs.putInteger(BEST, score);
		prefs.flush();
	}

	public static int getBestScore() {
		return prefs.getInteger(BEST, 0);
	}

	public static void setTime(float time) {
		prefs.putFloat(TIME, time);
		prefs.flush();
	}

	public static float getTime() {
		return prefs.getFloat(TIME, Setting.TIME_DEFAULT);
	}

}

package com.hungle.freakingcolor.screens;

import com.hungle.freakingcolor.assets.MyAssets;
import com.hungle.freakingcolor.objects.BlockGame;

public class ScreensManager {
	public static ScreensManager _instance;

	public static ScreensManager inst() {
		if (_instance == null) {
			_instance = new ScreensManager();
		}
		return _instance;
	}

	public BlockGame game;

	public void initialize(BlockGame game) {
		this.game = game;
	}

	public PlayGameScreen playGameScreen;
	public LoadingScreen loadingScreen;
	public MenuScreen menuScreen;
	public Splash splash;

	public ScreensManager() {

	}

	public Splash getSplash() {
		if (splash == null) {
			splash = new Splash();
		}
		return splash;
	}

	public PlayGameScreen getPlayGameScreen() {
		if (playGameScreen == null) {
			playGameScreen = new PlayGameScreen();
		}
		return playGameScreen;
	}
	
	public MenuScreen getMenuScreen(){
		if(menuScreen==null){
			menuScreen = new MenuScreen();
		}
		return menuScreen;
	}

	public LoadingScreen getLoadingScreen() {
		if (loadingScreen == null) {
			loadingScreen = new LoadingScreen();
		}
		return loadingScreen;
	}

	public void dispose() {
		System.out.println("ScreensManager--------dispose====");
		if (playGameScreen != null) {
			playGameScreen = null;
		}
		if (loadingScreen != null) {
			loadingScreen = null;
		}
		MyAssets.manager.clear();

		_instance = null;
	}
}

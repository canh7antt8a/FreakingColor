package com.hungle.freakingcolor.screens;

import com.hungle.freakingcolor.assets.MyAssets;

public class LoadingScreen extends BaseScreen {

	public LoadingScreen() {
		setActiveBackButton(true);
	}

	@Override
	public void show() {
		super.show();

		MyAssets.loadSoundGame();
		MyAssets.loadgame();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		MyAssets.manager.update();
		ScreensManager.inst().loadingScreen = null;
		ScreensManager.inst().game.setScreen(ScreensManager.inst().getSplash());
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

}

package com.hungle.freakingcolor.objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.hungle.freakingcolor.screens.ScreensManager;

public class BlockGame extends Game {
	public static BitmapFont font55,font100,font128;
	public ControlsApp control;

	public BlockGame(ControlsApp control) {
		super();
		this.control = control;
	}

	@Override
	public void create() {

		ScreensManager.inst().initialize(this);
		ScreensManager.inst().game.setScreen(ScreensManager.inst()
				.getLoadingScreen());

		font55 = new BitmapFont(Gdx.files.internal("data/font/font55.fnt"),
				Gdx.files.internal("data/font/font55.png"), false);
		font55.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		font100 = new BitmapFont(Gdx.files.internal("data/font/font100.fnt"),
				Gdx.files.internal("data/font/font100.png"), false);
		font100.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		font128 = new BitmapFont(Gdx.files.internal("data/font/font128.fnt"),
				Gdx.files.internal("data/font/font128.png"), false);
		font128.getRegion().getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		control.hidads();
		control.pushScreen("InGame");
	}

	@Override
	public void dispose() {
		System.out.println("BlockGame----dispose()----");
		ScreensManager.inst().dispose();
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

}

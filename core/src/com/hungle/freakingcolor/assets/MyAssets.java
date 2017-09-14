package com.hungle.freakingcolor.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MyAssets {
	public static MyAssets _instance;

	public static MyAssets inst() {
		if (_instance == null) {
			_instance = new MyAssets();
		}
		return _instance;
	}

	public static AssetManager manager = new AssetManager();

	public MyAssets() {
		manager = new AssetManager();
	}

	public static void loadgame() {
		manager.load("data/datapacker/FileMoTa.atlas", TextureAtlas.class);
	}

	public static void loadSoundGame() {
		manager.load("data/sound/click.mp3", Sound.class);
		manager.load("data/sound/fail.mp3", Sound.class);
		manager.load("data/sound/win.mp3", Sound.class);
	}

}

package com.hungle.freakingcolor.assets;

import com.badlogic.gdx.audio.Sound;

public class MySound {
	public static Sound touch;
	public static Sound fail;
	public static Sound win;
	
	public static void setSoundandMusic() {
		touch = MyAssets.manager.get("data/sound/click.mp3", Sound.class);
		fail = MyAssets.manager.get("data/sound/fail.mp3", Sound.class);
		win = MyAssets.manager.get("data/sound/win.mp3", Sound.class);
	}
}

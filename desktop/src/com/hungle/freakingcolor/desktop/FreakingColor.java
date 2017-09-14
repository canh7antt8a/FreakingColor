package com.hungle.freakingcolor.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hungle.freakingcolor.objects.BlockGame;

public class FreakingColor {
	
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Freaking Color";
		config.width = 420;
		config.height = 640;
		new LwjglApplication(new BlockGame(new DesktopControlsApp()), config);
	}
}

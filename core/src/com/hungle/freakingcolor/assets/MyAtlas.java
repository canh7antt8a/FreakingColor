package com.hungle.freakingcolor.assets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MyAtlas {
	public static TextureAtlas playGameAtlas;
	
	public static void setPlaygameAtlas(){
		if(MyAssets.manager.isLoaded("data/datapacker/FileMoTa.atlas",TextureAtlas.class)){
			playGameAtlas=MyAssets.manager.get("data/datapacker/FileMoTa.atlas",TextureAtlas.class);
		}
	}
}

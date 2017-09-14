package com.hungle.freakingcolor.screens;

import com.badlogic.gdx.Gdx;
import com.hungle.freakingcolor.group.MenuGroup;

public class MenuScreen extends BaseScreen{
	MenuGroup menuGroup;
	
	@Override
	public void show() {
		super.show();
		setActiveBackButton(true);
		
		menuGroup = new MenuGroup(BaseScreen.VIEWPORT_WIDTH, BaseScreen.VIEWPORT_HEIGHT);
		stage.addActor(menuGroup);
	}
	
	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		Gdx.app.exit();
	}
	
	
	@Override
	public void render(float delta) {
		super.render(delta);
	}
	
	@Override
	public void resize(int w, int h) {
		System.out.println("---resize in play screen----");
		super.resize(w, h);
	}

	@Override
	public void hide() {
		super.hide();
	}
	
}

package com.hungle.freakingcolor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.hungle.freakingcolor.assets.MyAssets;
import com.hungle.freakingcolor.assets.MyAtlas;
import com.hungle.freakingcolor.assets.MySound;

public class Splash extends BaseScreen {
	Texture iconTexture;
	Image iconImage;

	@Override
	public void show() {

		super.show();

		iconTexture = new Texture(
				Gdx.files.internal("data/datapacker/splash.png"));
		iconTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		iconImage = new Image(iconTexture);
		iconImage.setPosition((BaseScreen.VIEWPORT_WIDTH - iconImage.getWidth()) / 2,
				(BaseScreen.VIEWPORT_HEIGHT - iconImage.getHeight()) / 2);
		iconImage.getColor().a = 0;
		iconImage.addAction(Actions.sequence(Actions.fadeIn(2.0f)));
		stage.addActor(iconImage);
	}

	@Override
	public void render(float delta) {
		if (MyAssets.manager.update() && getStartTime() > 2.5f) {
			MyAtlas.setPlaygameAtlas();
			MySound.setSoundandMusic();
			ScreensManager.inst().game.setScreen(ScreensManager.inst()
					.getMenuScreen());

		}
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

	@Override
	public void keyBackPressed() {
	}

	@Override
	public void dispose() {
		super.dispose();
		iconTexture.dispose();
	}

}

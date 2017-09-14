package com.hungle.freakingcolor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class BaseScreen implements Screen, InputProcessor {

	public static int VIEWPORT_WIDTH = 800, VIEWPORT_HEIGHT = 1280;
	public static float ws,hs;
	public Stage stage;
	private float startTime;
	public boolean isChangeScreen = false;
	boolean isBackButtonActive;
	public final InputMultiplexer inputMultiplexer;

	public BaseScreen() {
		System.out.println("-----------base screen init-------------");
		startTime = 0;
		stage = new Stage(new ExtendViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
		this.inputMultiplexer = new InputMultiplexer(this);
		inputMultiplexer.addProcessor(stage);
		
		ws = stage.getCamera().viewportWidth;
		hs = stage.getCamera().viewportHeight;
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(221f / 255, 138f / 255, 19f / 255, 1f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		startTime += delta;
		stage.act(delta);
		stage.draw();
	}

	public float getStartTime() {
		return this.startTime;
	}

	@Override
	public void resize(int w, int h) {

		stage.setViewport(new ExtendViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT));
		stage.getViewport().update(w, h, true);
		System.out.println("---width: " + w + "---height:" + h);
		stage.getCamera().position.x = VIEWPORT_WIDTH / 2;
		stage.getCamera().position.y = VIEWPORT_HEIGHT / 2;
		stage.getCamera().update();

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		this.dispose();
		Gdx.app.log("BaseScreen", "dispose");
	}

	public void keyBackPressed() {
		Gdx.app.log("BaseScreen", "keyBackPressed");
	}

	public void setActiveBackButton(boolean _isActiveBackButton) {
		this.isBackButtonActive = _isActiveBackButton;
		Gdx.input.setCatchBackKey(isBackButtonActive);
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.BACK) {
			if (isBackButtonActive) {
				keyBackPressed();
			}
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}

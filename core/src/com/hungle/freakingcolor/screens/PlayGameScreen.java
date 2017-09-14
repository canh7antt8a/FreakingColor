package com.hungle.freakingcolor.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hungle.freakingcolor.assets.MySound;
import com.hungle.freakingcolor.group.PlayGameGroup;
import com.hungle.freakingcolor.group.ResultGroup;
import com.hungle.freakingcolor.utils.MyPrefs;
import com.hungle.freakingcolor.utils.Setting;

public class PlayGameScreen extends BaseScreen {

	private int currGroupShow = 0;

	final int GROUP_PLAY = 1;
	final int GROUP_QUIT = 2;
	final int GROUP_MENU = 3;
	final int GROUP_RESULT = 4;

	PlayGameGroup playGameGroup;
	ResultGroup resultGroup;
	private int score = 0;
	private int count = 0;
	private boolean ads = false;

	public boolean isStart = false;
	public SpriteBatch spriteBatch;

	@Override
	public void show() {
		setActiveBackButton(true);

		super.show();
		Setting.configTime = MyPrefs.getTime();

		spriteBatch = new SpriteBatch();

		playGameGroup = new PlayGameGroup(BaseScreen.VIEWPORT_WIDTH, BaseScreen.VIEWPORT_HEIGHT);
		stage.addActor(playGameGroup);
		startGameDemo();

		currGroupShow = GROUP_PLAY;

		resultGroup = new ResultGroup();
		stage.addActor(resultGroup);

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		playGameGroup.render(spriteBatch);
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
		super.keyBackPressed();
		// System.out.println("------keyBackPressed-------:" + currGroupShow);
		switch (currGroupShow) {
		case GROUP_MENU:
			break;
		case GROUP_PLAY:
			
			break;
		case GROUP_RESULT:
			Gdx.app.exit();
			break;
		default:
			break;
		}
	}

	public void setcurrGroupShow(int currGroupShow) {
		this.currGroupShow = currGroupShow;
	}

	public void startGame() {
		isStart = true;
		score = 0;
		playGameGroup.initContent(0);
		currGroupShow = GROUP_PLAY;
	}

	public void startGameDemo() {
		isStart = true;
		score = 0;
		playGameGroup.initDemo(0);
		currGroupShow = GROUP_PLAY;
	}

	public void setNext() {
		score++;
		playGameGroup.initContent(score);
	}

	public int getScore() {
		return this.score;
	}

	public void setFail() {
		isStart = false;
		if (score <= MyPrefs.getBestScore()) {

			if (MyPrefs.getSound()) {
				MySound.fail.play(1, 2, 0);
			}
		}
		resultGroup.moveIn(score);
		currGroupShow = GROUP_RESULT;
	}

	public void changeColor() {
		playGameGroup.changeColor();
	}
}

package com.hungle.freakingcolor.group;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.hungle.freakingcolor.assets.MyAtlas;
import com.hungle.freakingcolor.assets.MySound;
import com.hungle.freakingcolor.objects.BlockGame;
import com.hungle.freakingcolor.screens.BaseScreen;
import com.hungle.freakingcolor.screens.ScreensManager;
import com.hungle.freakingcolor.utils.MyPrefs;

public class ResultGroup extends Group  {
	public Image imageBg;
	public Image popup,newBestScore;
	public Image btnPlay, btnFacebook, btnTwitter;
	public Label txtScore, txtBestScore, hehe, haha;
	public Image loading;
	
	ParticleEffect e = new ParticleEffect();

	public ResultGroup() {
		super();
		this.setWidth(BaseScreen.VIEWPORT_WIDTH);
		this.setHeight(BaseScreen.VIEWPORT_WIDTH);
		init();
		this.setPosition(0, -BaseScreen.VIEWPORT_HEIGHT - 200);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		e.draw(batch,Gdx.graphics.getDeltaTime());
	}

	public void init() {
		
		e.load(Gdx.files.internal("winerxxx.p"),Gdx.files.internal(""));
		e.setPosition(BaseScreen.VIEWPORT_WIDTH/2,BaseScreen.VIEWPORT_HEIGHT/2+BaseScreen.hs/2);
		
		imageBg = new Image(MyAtlas.playGameAtlas.findRegion("bg"));
		imageBg.setWidth(BaseScreen.ws+100);
		imageBg.setHeight(BaseScreen.hs+100);
		imageBg.setPosition(BaseScreen.VIEWPORT_WIDTH/2-BaseScreen.ws/2-50,
				BaseScreen.VIEWPORT_HEIGHT/2-BaseScreen.hs/2-50);
		this.addActor(imageBg);
		imageBg.setColor(new Color(0f / 255, 0f / 255, 0f / 255, 0f / 255));

		popup = new Image(MyAtlas.playGameAtlas.findRegion("gameover"));
		popup.setX(BaseScreen.VIEWPORT_WIDTH / 2 - popup.getWidth() / 2);
		popup.setY(BaseScreen.VIEWPORT_HEIGHT/2 - popup.getHeight()/2);
		this.addActor(popup);
		
		loading = new Image(MyAtlas.playGameAtlas.findRegion("loading"));
		loading.setPosition(getWidth()/2-loading.getWidth()/2,getHeight()/2-BaseScreen.hs/2-2*getHeight());
		this.addActor(loading);
		
		newBestScore = new Image(MyAtlas.playGameAtlas.findRegion("new"));
		newBestScore.setPosition(BaseScreen.VIEWPORT_WIDTH/2-20,BaseScreen.VIEWPORT_HEIGHT/2+25);
		this.addActor(newBestScore);
		newBestScore.setVisible(false);

		LabelStyle styleScore = new LabelStyle();
		styleScore.font = BlockGame.font100;
		txtScore = new Label("0", styleScore);
		txtScore.setAlignment(Align.center);
		txtScore.setColor(60f / 255, 76f / 255, 110f / 255, 1f);
		txtScore.setPosition(BaseScreen.VIEWPORT_WIDTH/2-20,BaseScreen.VIEWPORT_HEIGHT/2+25);
		this.addActor(txtScore);

		LabelStyle styleBest = new LabelStyle();
		styleBest.font = BlockGame.font55;
		txtBestScore = new Label("0", styleBest);
		txtBestScore.setAlignment(Align.center);
		txtBestScore.setColor(60f / 255, 76f / 255, 110f / 255, 1f);
		txtBestScore.setPosition(BaseScreen.VIEWPORT_WIDTH/2-10,BaseScreen.VIEWPORT_HEIGHT/2-30);
		this.addActor(txtBestScore);

		btnPlay = new Image(MyAtlas.playGameAtlas.findRegion("replay"));
		btnPlay.setX(BaseScreen.VIEWPORT_WIDTH / 2 - btnPlay.getWidth() / 2);
		btnPlay.setY(popup.getY()+20);
		btnPlay.setOrigin(btnPlay.getWidth()/2, btnPlay.getHeight()/2);
		MyClickListener clickPlay = new MyClickListener(new Runnable() {
			public void run() {
				System.out.println("ABCD");
				if(1 != 1) {
					ScreensManager.inst().playGameScreen.startGameDemo();
				} else {
					ScreensManager.inst().game.control.purcharseIAP(false);
					// ScreensManager.inst().game.control.purcharseIAP(false);
				}

			}
		});
		btnPlay.addListener(clickPlay);
		this.addActor(btnPlay);

		btnFacebook = new Image(MyAtlas.playGameAtlas.findRegion("facebook"));
		btnFacebook.setX(BaseScreen.VIEWPORT_WIDTH / 2 + 150);
		btnFacebook.setY(popup.getY()+35);
		btnFacebook.addListener(btnClick);
		this.addActor(btnFacebook);

		btnTwitter = new Image(MyAtlas.playGameAtlas.findRegion("twitter"));
		btnTwitter.setX(BaseScreen.VIEWPORT_WIDTH / 2 - 150 - btnTwitter.getWidth());
		btnTwitter.setY(popup.getY()+35);
		btnTwitter.addListener(btnClick);
		this.addActor(btnTwitter);
	}

	private void scoreAnimation(final int score, final int index) {
		Timer.schedule(new Task() {

			@Override
			public void run() {
				if (index <= score) {
					txtScore.setText(Integer.toString(index));
					scoreAnimation(score, index + 1);
				}
			}
		}, .05f);
	}

	public void setScore(int score) {
		if(score<5){
			txtScore.setText(Integer.toString(score));
		}else{
			scoreAnimation(score, 0);
		}
		txtBestScore.setText(Integer.toString(MyPrefs.getBestScore()));
	}

	ClickListener btnClick = new ClickListener() {

		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			super.touchUp(event, x, y, pointer, button);
			Image image = (Image) event.getTarget();
		    if (image == btnFacebook) {
		    	loading.addAction(Actions.sequence(
		    			Actions.moveTo(loading.getX(),
		    			BaseScreen.VIEWPORT_HEIGHT/2-BaseScreen.hs/2+30),
		    			Actions.run(new Runnable() {
							public void run() {
								ScreensManager.inst().game.control.shareFacebook(MyPrefs.getBestScore());
							}
						}),
		    			Actions.delay(0.5f),
		    			Actions.moveTo(loading.getX(),
		    					BaseScreen.VIEWPORT_HEIGHT/2-BaseScreen.hs/2-2*loading.getHeight(),
		    					0.5f,Interpolation.sineOut))); 

			} else if (image == btnTwitter) {
				loading.addAction(Actions.sequence(
		    			Actions.moveTo(loading.getX(),
		    			BaseScreen.VIEWPORT_HEIGHT/2-BaseScreen.hs/2+30),
		    			Actions.run(new Runnable() {
							public void run() {
								ScreensManager.inst().game.control.shareTwitter(MyPrefs.getBestScore());
							}
						}),
		    			Actions.delay(0.5f),
		    			Actions.moveTo(loading.getX(),
		    					BaseScreen.VIEWPORT_HEIGHT/2-BaseScreen.hs/2-2*loading.getHeight(),
		    					0.5f,Interpolation.sineOut))); 
			}
		}
	};

	public void moveIn(final int score) {
		if(score<=MyPrefs.getBestScore()){
			if (MyPrefs.getSound()) {
				MySound.fail.play(1, 2, 0);
			}
		}
		imageBg.getColor().a = 0f;
		this.addAction(Actions.sequence(
				Actions.moveTo(0, -20, 0.5f), Actions.moveTo(0, 20, 0.1f),
				Actions.moveTo(0, 0, 0.1f), Actions.run(new Runnable() {

					@Override
					public void run() {
						ScreensManager.inst().game.control.showads();
						ScreensManager.inst().game.control.pushActions("GameOver", Integer.toString(score));
						txtScore.setVisible(true);
						imageBg.getColor().a = 220f / 255;
						setScore(score);
						if(score>MyPrefs.getBestScore()){
							MyPrefs.setBestScore(score);
							newBestScore.setVisible(true);
							e.start();
							if(MyPrefs.getSound()){
								MySound.win.play(0.5f);
							}
						}
					}
				})));
			
	}

	public void moveOut() {
		imageBg.getColor().a = 0f;
		addAction(Actions.sequence(
				Actions.moveTo(this.getX(), -BaseScreen.VIEWPORT_HEIGHT -200),
				Actions.run(new Runnable() {

					@Override
					public void run() {
						ScreensManager.inst().game.control.hidads();
						newBestScore.setVisible(false);
						txtScore.setVisible(false);
					}
				})));
	}

}

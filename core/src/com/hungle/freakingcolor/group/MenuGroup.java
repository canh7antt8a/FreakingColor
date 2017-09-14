package com.hungle.freakingcolor.group;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hungle.freakingcolor.assets.MyAtlas;
import com.hungle.freakingcolor.screens.BaseScreen;
import com.hungle.freakingcolor.screens.ScreensManager;
import com.hungle.freakingcolor.utils.MyColor;
import com.hungle.freakingcolor.utils.MyPrefs;

public class MenuGroup extends Group {
	public Image imageBg, logo,lehung,newgame;
	public Image btnPlay, btnRate, btnMoreapp;

	public Label hehe, haha;
	public Label l;
	
	public MenuGroup(int w,int h) {
		super();
		this.setWidth(w);
		this.setHeight(h);
		this.setPosition(0,0);
		init(w,h);
	}
	
	public void init(int w,int h) {
		imageBg = new Image(MyAtlas.playGameAtlas.findRegion("bg"));
		imageBg.setWidth(BaseScreen.ws);
		imageBg.setHeight(BaseScreen.hs);
		imageBg.setPosition(w/2-BaseScreen.ws/2,h/2-BaseScreen.hs/2);
		this.addActor(imageBg);
		imageBg.setColor(MyColor.parseColor("ff8400"));
		
		newgame = new Image(MyAtlas.playGameAtlas.findRegion("newgame"));
		newgame.setPosition(w/2-BaseScreen.ws/2+20,h/2-BaseScreen.hs/2+20);
		if(!MyPrefs.getdisableAnimNewGame()){
			newgame.addAction(Actions.forever(Actions.sequence(
					Actions.scaleTo(1.05f,1.05f,0.3f),
					Actions.scaleTo(1.0f,1.0f,0.3f))));
		}
		newgame.addListener(click);
		this.addActor(newgame);
		
		logo = new Image(MyAtlas.playGameAtlas.findRegion("freakingcolor"));
		logo.setX(BaseScreen.VIEWPORT_WIDTH / 2 - logo.getWidth() / 2);
		logo.setY(h-200-logo.getHeight());
		this.addActor(logo);
		
		Image urate = new Image(MyAtlas.playGameAtlas.findRegion("bg"));
		urate.setColor(MyColor.parseColor("474d81"));
		urate.setSize(w/2-100,MyAtlas.playGameAtlas.findRegion("rate").getRegionHeight()-2);
		urate.setPosition(w / 2 - BaseScreen.ws/2,280);
		urate.setOriginX(0);
		urate.setScale(0.1f,1);
		urate.addAction(Actions.scaleTo(1, 1, 0.5f,Interpolation.sineOut));
		this.addActor(urate);
		
		btnRate = new Image(MyAtlas.playGameAtlas.findRegion("rate"));
		btnRate.setX(w/2-BaseScreen.ws/2);
		btnRate.setY(280);
		btnRate.setOrigin(btnRate.getWidth()/2, btnRate.getHeight()/2); 
		btnRate.addAction(Actions.sequence(
				Actions.moveTo(
				w / 2 - BaseScreen.ws/2+w/2-100 - btnRate.getWidth()/2,
				280,0.5f,Interpolation.sineOut),
				Actions.rotateTo(15,0.1f),
				Actions.rotateTo(-15,0.2f),
				Actions.rotateTo(0,0.1f))); 
		this.addActor(btnRate);
		btnRate.addListener(click);
		
		Image umore = new Image(MyAtlas.playGameAtlas.findRegion("bg"));
		umore.setColor(MyColor.parseColor("474d81"));
		umore.setSize(BaseScreen.ws/2-70,MyAtlas.playGameAtlas.findRegion("more").getRegionHeight()-2);
		umore.setPosition(w/2 + 70+MyAtlas.playGameAtlas.findRegion("more").getRegionWidth()/2,180);
		umore.setOriginX(umore.getWidth());
		umore.setScale(0.1f,1); 
		umore.addAction(Actions.scaleTo(1, 1, 0.5f,Interpolation.sineOut));
		this.addActor(umore);
		
		btnMoreapp = new Image(MyAtlas.playGameAtlas.findRegion("more"));
		btnMoreapp.setX(w/ 2 + BaseScreen.ws/2-btnMoreapp.getHeight()/2);
		btnMoreapp.setY(180);
		btnMoreapp.setOrigin(btnMoreapp.getWidth()/2, btnMoreapp.getHeight()/2); 
		btnMoreapp.addAction(Actions.sequence(
				Actions.moveTo(w/ 2 + 70,180,0.5f,Interpolation.sineOut),
				Actions.delay(0.8f),
				Actions.scaleTo(1.2f, 1.2f,0.1f),
				Actions.scaleTo(1, 1,0.2f))); 
		this.addActor(btnMoreapp);
		btnMoreapp.addListener(click);
		
		btnPlay = new Image(MyAtlas.playGameAtlas.findRegion("play"));
		btnPlay.setX(BaseScreen.VIEWPORT_WIDTH / 2 - btnPlay.getWidth() / 2);
		btnPlay.setY(500);
		btnPlay.setOrigin(btnPlay.getWidth()/2,btnPlay.getHeight()/2);
		this.addActor(btnPlay);
		ClickListener listenerPlay = new ClickListener(){
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				MyPrefs.setDemo(false);
				ScreensManager.inst().game.setScreen(ScreensManager.inst()
						.getPlayGameScreen());
				moveOut();
				super.touchUp(event, x, y, pointer, button);
			};
		};
		btnPlay.addListener(listenerPlay);
		
		lehung = new Image(MyAtlas.playGameAtlas.findRegion("lehung"));
		lehung.setPosition(w/2+BaseScreen.ws/2-lehung.getWidth()-20,h/2-BaseScreen.hs/2+20);
		this.addActor(lehung);

	}

	ClickListener click = new ClickListener(){
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			Image image = (Image)event.getTarget();
			if(image==btnRate){
				ScreensManager.inst().game.control.rateGame();
			}else if(image == btnMoreapp){
				ScreensManager.inst().game.control.moreApp();
				ScreensManager.inst().game.control.pushActions("Click CircleJumping", "xx");
			}else if (image == newgame){
				if(!MyPrefs.getdisableAnimNewGame()){
					MyPrefs.disableAnimNewGame(true);
				}
				ScreensManager.inst().game.control.newgameView();
			}
			return super.touchDown(event, x, y, pointer, button);
		};
	};

	public void moveOut() {
		this.addAction(Actions.sequence(Actions.moveTo(this.getX(), 0), Actions
				.moveTo(this.getX(), -BaseScreen.VIEWPORT_HEIGHT - 200, 0.5f),
				Actions.run(new Runnable() {

					@Override
					public void run() {
					}
				})));
	}

}

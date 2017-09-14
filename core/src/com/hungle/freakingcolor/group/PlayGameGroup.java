package com.hungle.freakingcolor.group;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.hungle.freakingcolor.assets.MyAtlas;
import com.hungle.freakingcolor.assets.MySound;
import com.hungle.freakingcolor.objects.AnimCircle;
import com.hungle.freakingcolor.objects.BlockGame;
import com.hungle.freakingcolor.objects.PPImage;
import com.hungle.freakingcolor.screens.BaseScreen;
import com.hungle.freakingcolor.screens.ScreensManager;
import com.hungle.freakingcolor.utils.MyColor;
import com.hungle.freakingcolor.utils.MyPrefs;
import com.hungle.freakingcolor.utils.Setting;

public class PlayGameGroup extends Group {
	private PPImage imageBg;
	private PPImage btnSound;
	private PPImage timePlay;
	
	private Label txtScore;
	private int angle,_angle;
	private float size;

	private PPImage[] imageItem;
	private PPImage boardfull;
	private int n, index;
	private float color1, color2, color3;
	private float timeSet;
	
	private AnimCircle animCircle;
	
	private boolean checkCount;
	
	Container<Label> l;

	public PlayGameGroup(float w, float h) {
		super();
		
		this.setWidth(w);
		this.setHeight(h);
		initMenu(w,h);
		
		timePlay = new PPImage(MyAtlas.playGameAtlas.findRegion("bg"));
		timePlay.setWidth(BaseScreen.ws);
		timePlay.setHeight(25);
		timePlay.setPosition(w/2-BaseScreen.ws/2,h/2+BaseScreen.hs/2-25);
		timePlay.setOriginX(0);
		this.addActor(timePlay);

		animCircle = new AnimCircle();
		
		boardfull = new PPImage(
				MyAtlas.playGameAtlas.findRegion("board"));
		this.addActor(boardfull);
		
		timeSet = Setting.TIME_DEFAULT;
	}

	private void newdata() {
		if(!checkCount){
			if (n < 9) {
				n++;
				if(n==4||n==6||n==8){
					timeSet-=0.5f;
					System.out.println("time: "+timeSet);
				}
			}else{
				checkCount = true;
			}
			
		}else{
			System.out.println("time: "+timeSet);
			n = random(9, 2);
			
		}
		index = random(n * n, 0);
		color1 = random(200, 10);
		color2 = random(200, 10);
		color3 = random(200, 10);
		

	}
	
	public void initContent(int score) {
		removeBoard();
		newdata();
		setboardlevel(this.getWidth(), this.getHeight());
		txtScore.setText(Integer.toString(score));
		l.addAction(Actions.sequence(
				Actions.scaleTo(1.3f, 1.3f,0.1f),
				Actions.scaleTo(1, 1,0.2f)));
		
		timePlay.setColor(MyColor.xam);
		timePlay.setScale(1f, 1f);
	}

	public void initDemo(int score) {

		checkCount = false;
		PlayGameGroup.this.setTouchable(Touchable.enabled);
		removeBoard();
		n = 1;
		_angle = 2;
		timeSet = Setting.TIME_DEFAULT;
		newdata();
		setboardlevel(this.getWidth(), this.getHeight());
		txtScore.setText(Integer.toString(score));
		l.addAction(Actions.sequence(
				Actions.scaleTo(1.1f, 1.1f,0.3f),
				Actions.scaleTo(1, 1,0.2f)));
		
		timePlay.setColor(MyColor.xam);
		timePlay.setScale(1f, 1f);
	}

	public void initMenu(float w, float h) {

		imageBg = new PPImage(MyAtlas.playGameAtlas.findRegion("bg"));
		imageBg.setSize(BaseScreen.ws,BaseScreen.hs);
		imageBg.setPosition(w/2-BaseScreen.ws/2,h/2-BaseScreen.hs/2);
		imageBg.setColor(MyColor.parseColor("ff8400"));
		this.addActor(imageBg);

		btnSound = new PPImage(MyAtlas.playGameAtlas.findRegion("sound_on"));
		btnSound.setPosition(35,h-55-btnSound.getHeight());
		btnSound.addListener(PPImageClick);
		if (!MyPrefs.getSound()) {
			btnSound.setDrawable(new SpriteDrawable(
					new Sprite(MyAtlas.playGameAtlas.findRegion("sound_off"))));
		}
		this.addActor(btnSound);
		
		

		LabelStyle lbStyle = new LabelStyle();
		lbStyle.font =  BlockGame.font128;
		//lbStyle.fontColor = MyColor.parseColor("1c4d78");
		txtScore = new Label("00", lbStyle);
		txtScore.setAlignment(Align.center);
		l = new Container<Label>(txtScore);
		l.setTransform(true);
		l.setPosition(w-90,h-btnSound.getHeight());
		
		this.addActor(l);
		setboard();

	}
	
	public void render(SpriteBatch spriteBatch){
		spriteBatch.begin();
		spriteBatch.setProjectionMatrix(getStage().getCamera().combined);
		if(!MyPrefs.getDemo()){
			
			if(angle == 20){
				_angle=-2;
			}else if(angle ==-20){
				_angle=2;
			}
			angle +=_angle;
			spriteBatch.setColor(Color.WHITE);
			spriteBatch.draw(MyAtlas.playGameAtlas.findRegion("v"),
					imageItem[index].getX()+3, imageItem[index].getY()+3,
					size / 6, size / 6,size / 3, size / 3,1,1,angle);
		}
		animCircle.draw(spriteBatch);
		animCircle.update(Gdx.graphics.getDeltaTime()*1000);
		spriteBatch.end();
	}

	public void setboard() {
		newdata();
		imageItem = new PPImage[n * n];
		for (int i = 0; i < n * n; i++) {
			imageItem[i] = new PPImage();
			this.addActor(imageItem[i]);
		}
	}

	private void setboardlevel(float w, float h) {

		imageItem = new PPImage[n * n];
		size = (w - 14) / n - 4;
		
		
		boardfull.setSize(w-4, w-4);
		boardfull.setPosition(2,h/2-w/2+2);
		
		for (int i = 0; i < n * n; i++) {
			final int x = i % n;
			final int y = i / n;
			
			imageItem[i] = new PPImage(MyAtlas.playGameAtlas.findRegion("item"+n));
			imageItem[i].setSize(size, size);
			imageItem[i].setPosition(9.0f + (float) (x * (size + 4)),
					9.0f + h/2-w/2 + (float) (y * (size + 4)));
			imageItem[i].setOrigin(size / 2, size / 2);

			if (i == index) {
				imageItem[i].setColor((color1 + random(10, 10)) / 255,
						(color2 + random(10, 10)) / 255,
						(color3 + random(5,10)) / 255, 1);
			} else {
				imageItem[i].setColor(color1 / 255, color2 / 255, color3 / 255, 1);
			}
			imageItem[i].addListener(PPImageClick);
			this.addActor(imageItem[i]);
		}
		

	}

	ClickListener PPImageClick = new ClickListener() {

		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			PPImage im = (PPImage) event.getTarget();
			if (im == btnSound) {
				if (MyPrefs.getSound()) {
					MyPrefs.setSound(false);
					btnSound.setTextureRegion(MyAtlas.playGameAtlas
							.findRegion("sound_off"));
				} else {
					MyPrefs.setSound(true);
					btnSound.setTextureRegion(MyAtlas.playGameAtlas
							.findRegion("sound_on"));
				}
			} else if (im == imageItem[index]) {
				if (MyPrefs.getSound()) {
					MySound.touch.play();
				}
				MyPrefs.setDemo(true); 
				
				animCircle.setup(im.getX()+size/2,im.getY()+size/2);
				imageItem[index].addAction(Actions.sequence(
						Actions.scaleTo(0.85f, 0.85f,0.1f),
						Actions.scaleTo(1, 1,0.1f),
						Actions.run(new Runnable() {
							public void run() {
								if (ScreensManager.inst().playGameScreen.isStart) {
									ScreensManager.inst().playGameScreen.setNext();
									setTimePlay();
								}
							}
						}))); 
				
			}
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			super.touchUp(event, x, y, pointer, button);
		}

	};

	public void setTimePlay() {
		this.timePlay.clearActions();
		float timeRight = timeSet * 0.7f;
		timePlay.addAction(Actions.sequence(Actions.scaleTo(1f, 1f),
				Actions.scaleTo(0.3f, 1f, timeRight),
				Actions.run(new Runnable() {
					public void run() {
						timePlay.setColor(MyColor.red);
					}
				}), Actions.scaleTo(0f, 1f, timeSet - timeRight),
				Actions.run(new Runnable() {
					public void run() {
						ScreensManager.inst().playGameScreen.setFail();
						checkCount = false;
						//MyPrefs.setDemo(false);
						PlayGameGroup.this.setTouchable(Touchable.disabled);
					}
				})));
	}
	
	public int getLocation(int n,float x,float y){
		for(int i=0;i<n*n;i++){
			int t = i%n;
			int k = i/n;
			
			if(x>=9.0f + (float) (t * (size + 4))
					&& x<= 9.0f + (float) (t * (size + 4))+size
					&& y >=9.0f + getHeight()/2-getWidth()/2 + (float) (k * (size + 4))
					&& y <=9.0f + getHeight()/2-getWidth()/2 + (float) (k * (size + 4))+size){
				return i;
			}
		}
		return -1;
	}

	private int random(int u, int v) {
		Random r = new Random();
		return (int) (Math.abs(r.nextInt()) % u + v);
	}

	public void removeBoard() {
		for (int i = 0; i < n * n; i++) {
			this.removeActor(imageItem[i]);
		}
	}

	public void changeColor() {
		//imageBg.setColor(MyColor.getPlayColor());
	}

}

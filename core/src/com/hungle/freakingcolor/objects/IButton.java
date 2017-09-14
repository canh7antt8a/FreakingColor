package com.hungle.freakingcolor.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class IButton extends ImageButton{
	
	public enum PressEffect {
		
		BUBBLE,
		FADE,
		COLORIZE
	}
	
	private PressEffect pressEffect = PressEffect.BUBBLE;
	private InputListener inputListener;
	
	private float scalePressedEffect = 0.2f;
	private Color colorPressedEffect = new Color(0.6f, 0.6f, 0.6f, 0.8f);
	private float alphaPressedEffect = 0.6f;
	private float effectDuration = 0.1f;
	
	public IButton(Drawable imageUp, Drawable imageDown,
			Drawable imageChecked) {
		super(imageUp, imageDown, imageChecked);
	}

	public IButton(Drawable imageUp, Drawable imageDown) {
		super(imageUp, imageDown);
	}

	public IButton(Drawable imageUp) {
		super(imageUp);
	}

	public IButton(Skin skin, String styleName) {
		super(skin, styleName);
	}

	public IButton(Skin skin) {
		super(skin);
	}

	public IButton(ImageButtonStyle style) {
		super(style);
		 
	}
	
	{
		setTransform(true);
		inputListener = new InputListener(){
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				
				if(isPressed()) {
					effectIn();
				}
				return super.touchDown(event, x, y, pointer, button);
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				effectOut();
			}
		};
		addListener(inputListener);
	}
	
	public PressEffect getPressEffect() {
		return pressEffect;
	}

	public void setPressEffect(PressEffect pressEffect) {
		this.pressEffect = pressEffect;
	}

	public float getScalePressedEffect() {
		return scalePressedEffect;
	}

	public void setScalePressedEffect(float scalePressedEffect) {
		this.scalePressedEffect = scalePressedEffect;
	}

	public Color getColorPressedEffect() {
		return colorPressedEffect;
	}

	public void setColorPressedEffect(Color colorPressedEffect) {
		this.colorPressedEffect = colorPressedEffect;
	}

	public float getAlphaPressedEffect() {
		return alphaPressedEffect;
	}

	public void setAlphaPressedEffect(float alphaPressedEffect) {
		this.alphaPressedEffect = alphaPressedEffect;
	}

	public float getEffectDuration() {
		return effectDuration;
	}

	public void setEffectDuration(float effectDuration) {
		this.effectDuration = effectDuration;
	}

	@Override
	public void setSize(float width, float height) {
		
		super.setSize(width, height);
		setOrigin(width/2, height/2);
	}
	
	private void effectIn() {
		
		switch (pressEffect) {
		case BUBBLE:
			bubbleIn();
			break;

		case FADE:
			fadeIn();
			break;
			
		case COLORIZE:
			colorIn();
			break;
		default:
			bubbleIn();
			break;
		}
	}
	
	private void effectOut() {
		
		switch (pressEffect) {
		case BUBBLE:
			bubbleOut();
			break;

		case FADE:
			fadeOut();
			break;
			
		case COLORIZE:
			colorOut();
			break;
		default:
			bubbleOut();
			break;
		}
	}
	
	private void bubbleIn() {
		this.addAction(Actions.scaleTo(
				1 + scalePressedEffect, 1 + scalePressedEffect, effectDuration,
				Interpolation.elastic));
	}
	
	private void bubbleOut() {
		this.addAction(Actions.scaleTo(
				1, 1, effectDuration));
	}
	
	private void fadeIn() {
		addAction(Actions.alpha(
				alphaPressedEffect, effectDuration));
	}
	
	private void fadeOut() {
		
		addAction(Actions.alpha(1, effectDuration));
	}
	
	private void colorIn() {
		
		getImage().addAction(Actions.color(colorPressedEffect, effectDuration));
	}
	
	private void colorOut() {
		
		getImage().addAction(Actions.color(Color.WHITE, effectDuration));
	}


}

package com.hungle.freakingcolor.objects;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PPImage extends Image {
	private TextureRegion textureRegion;

	public PPImage(TextureRegion textureRegion) {
		super();
		this.textureRegion = textureRegion;
		this.textureRegion.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
		setOrigin(getWidth() / 2, getHeight() / 2);
	}

	public PPImage() {
		super();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (textureRegion != null) {
			batch.draw(textureRegion, getX(), getY(), getOriginX(),
					getOriginY(), getWidth(), getHeight(), getScaleX(),
					getScaleY(), getRotation());
		}
	}

	public TextureRegion getTextureRegion() {
		return textureRegion;
	}

	public void setTextureRegion(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
	}
}

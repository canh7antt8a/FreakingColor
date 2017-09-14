package com.hungle.freakingcolor.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderUtil {
    static String vertexShader = "attribute vec4 a_position;\n" +
            "attribute vec4 a_color;\n" +
            "attribute vec2 a_texCoord0;\n" +
            "\n" +
            "uniform mat4 u_projTrans;\n" +
            "\n" +
            "varying vec4 v_color;\n" +
            "varying vec2 v_texCoords;\n" +
            "\n" +
            "void main() {\n" +
            "    v_color = a_color;\n" +
            "    v_texCoords = a_texCoord0;\n" +
            "    gl_Position = u_projTrans * a_position;\n" +
            "}";

    static String fragmentShader = "#ifdef GL_ES\n" +
            "    precision mediump float;\n" +
            "#endif\n" +
            "\n" +
            "varying vec4 v_color;\n" +
            "varying vec2 v_texCoords;\n" +
            "uniform sampler2D u_texture;\n" +
            "\n" +
            "void main() {\n" +
            "  vec4 c = v_color * texture2D(u_texture, v_texCoords);\n" +
            "  float grey = (c.r + c.g + c.b) / 3.0;\n" +
            "  gl_FragColor = vec4(grey, grey, grey, c.a);\n" +
            "}";

    public static ShaderProgram grayscaleShader;
    
	static final String brightnessvertexShader = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
			+ "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
			+ "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
			+ "uniform mat4 u_projTrans;\n" //
			+ "varying vec4 v_color;\n" //
			+ "varying vec2 v_texCoords;\n" //
			+ "\n" //
			+ "void main()\n" //
			+ "{\n" //
			+ "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n" //
			+ "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n" //
			+ "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" //
			+ "}\n";
		
		static final String brightnessfragmentShader = "#ifdef GL_ES\n" //
			+ "#define LOWP lowp\n" //
			+ "precision mediump float;\n" //
			+ "#else\n" //
			+ "#define LOWP \n" //
			+ "#endif\n" //
			+ "varying LOWP vec4 v_color;\n" //
			+ "varying vec2 v_texCoords;\n" //
			+ "uniform sampler2D u_texture;\n" //
			+ "uniform float brightness;\n" //
			+ "uniform float contrast;\n" //
			+ "void main()\n"//
			+ "{\n" //
			+ "  vec4 color = v_color * texture2D(u_texture, v_texCoords);\n" 
			+ "  color.rgb /= color.a;\n" //ignore alpha
			+ "  color.rgb = ((color.rgb - 0.5) * max(contrast, 0.0)) + 0.5;\n" //apply contrast
			+ "  color.rgb += brightness;\n" //apply brightness
			+ "  color.rgb *= color.a;\n" //return alpha
			+ "  gl_FragColor = color;\n"
			+ "}";
		
	    public static ShaderProgram brightnessShader;
	    
	    public static ShaderProgram blurShader;
	    
	    public static void init() {
	    	
	    	grayscaleShader = new ShaderProgram(vertexShader,
	                fragmentShader);
	    	brightnessShader = new ShaderProgram(brightnessvertexShader,
		            brightnessfragmentShader);
	    	blurShader = new ShaderProgram(
		            Gdx.files.internal("data/shader/blur.vert").readString(),
		            Gdx.files.internal("data/shader/blur.frag").readString());
	    }
}

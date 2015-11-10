package com.bittground.ivanandigor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.bittground.ivanandigor.Screens.GameScreen;

import java.util.HashMap;

/**
 * Created by Cole on 9/25/2015.
 */
public class IvanAndIgor extends Game {

    public SpriteBatch batch;
    public static HashMap<String, TextureAtlas> atlases;

    @Override
    public void create () {

        batch = new SpriteBatch();

        //Load Assets
        atlases = new HashMap<String, TextureAtlas>();
        atlases.put("tiles", new TextureAtlas(Gdx.files.internal("testpack.pack")));
        atlases.put("robots", new TextureAtlas(Gdx.files.internal("robots.pack")));
        atlases.put("robotsfull", new TextureAtlas(Gdx.files.internal("robotsfull.pack")));
        setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }
}
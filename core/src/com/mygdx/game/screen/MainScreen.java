package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GdxGame;

public class MainScreen implements Screen {

    private static float SPEED = 100;

    private final GdxGame game;
    private Texture img;
    private float x;
    private float y;

    public MainScreen(GdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        float step = SPEED* Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            y+=step;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y-=step;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x-=step;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x+=step;
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        game.batch.draw(img, x, y);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

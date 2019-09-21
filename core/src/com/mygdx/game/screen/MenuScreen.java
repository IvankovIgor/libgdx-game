package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GdxGame;

public class MenuScreen implements Screen {

    private final GdxGame game;
    private final Texture backgroundTexture;
    private final Texture playTexture;
    private final Texture background;

    public MenuScreen(GdxGame game) {
        this.game = game;
        this.backgroundTexture = new Texture("background.png");
        this.playTexture = new Texture("play.png");
        this.background = new Texture("editor.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0,0,720,480);
        game.batch.draw(playTexture, 232,256,256,128);
        game.batch.draw(background, 232,100,256,128);
        game.batch.end();
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

package com.mygdx.game.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GdxGame;

public class MenuScreen implements Screen {

    private final GdxGame game;
    private Texture backgroundTexture;
    private Stage stage;

    public MenuScreen(GdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        this.stage = new Stage(new ScreenViewport());
        this.backgroundTexture = new Texture("background.png");


        Button playButton = createButton("play.png", game::enterMainScreen);
        playButton.setPosition(232, 256);
        stage.addActor(playButton);

        Button editorButton = createButton("editor.png", game::enterEditorScreen);
        editorButton.setPosition(232, 100);
        stage.addActor(editorButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.getBatch().begin();
        stage.getBatch().draw(backgroundTexture, 0, 0, 720, 480);
        stage.getBatch().end();
        stage.draw();
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

    private ImageButton createButton(String imagePath, Runnable runnable) {
        Texture playTexture = new Texture(imagePath);
        TextureRegion textureRegion = new TextureRegion(playTexture);
        TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(textureRegion);
        ImageButton button = new ImageButton(myTexRegionDrawable);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                runnable.run();
                super.clicked(event, x, y);
            }
        });
        return button;
    }
}

package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.ui.screen.EditorScreen;
import com.mygdx.game.ui.screen.MainScreen;
import com.mygdx.game.ui.screen.MenuScreen;

public class GdxGame extends Game {

    public static final int HEIGHT = 480;
    public static final int WIDTH = 720;

    private final Screen menuScreen = new MenuScreen(this);
    private final Screen mainScreen = new MainScreen(this);
    private final Screen editorScreen = new EditorScreen(this);

    @Override
    public void create() {
        setScreen(menuScreen);
    }

    @Override
    public void render() {
        super.render();
    }

    public void enterMainScreen() {
        setScreen(mainScreen);
    }

    public void enterEditorScreen() {
        setScreen(editorScreen);
    }
}

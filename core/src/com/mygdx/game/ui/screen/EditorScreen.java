package com.mygdx.game.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.mygdx.game.GdxGame;
import com.strongjoshua.console.Console;
import com.strongjoshua.console.GUIConsole;

import static com.mygdx.game.GdxGame.HEIGHT;
import static com.mygdx.game.GdxGame.WIDTH;

public class EditorScreen implements Screen {

    private final GdxGame game;
    private PerspectiveCamera camera;
    private ModelBatch modelBatch;
    private CameraInputController cameraInputController;
    private Environment environment;
    private Console console;

    public EditorScreen(GdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1, 1, 1, 1));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        this.console = new GUIConsole();
        this.console.setPosition(0, 0);
        this.console.setSize(300, 300);
        this.console.enableSubmitButton(true);
        this.console.getWindow().setMovable(false);
        this.console.log("TEST");

        modelBatch = new ModelBatch();

        camera = new PerspectiveCamera(67, WIDTH, HEIGHT);
        camera.position.set(5, 5, 5);
        camera.lookAt(0, 0, 0);
        camera.far = 100;
        camera.near = 1;

        cameraInputController = new CameraInputController(camera) {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.C) {
                    console.setVisible(true);
                    Gdx.input.setInputProcessor(console.getInputProcessor());
                }
                return super.keyDown(keycode);
            }
        };
        cameraInputController.autoUpdate = true;
        Gdx.input.setInputProcessor(cameraInputController);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        cameraInputController.update();

        modelBatch.begin(camera);
        modelBatch.render(createTestBox(), environment);
        modelBatch.end();

        console.draw();
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

    private ModelInstance createTestBox() {
        return new ModelInstance(new ModelBuilder().createBox(
                5,
                5,
                5,
                new Material(
                        ColorAttribute.createDiffuse(Color.GRAY),
                        ColorAttribute.createSpecular(Color.BLUE),
                        FloatAttribute.createShininess(16f)
                ),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
        ));
    }
}

package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.mygdx.game.GdxGame;

import static com.mygdx.game.GdxGame.HEIGHT;
import static com.mygdx.game.GdxGame.WIDTH;

public class EditorScreen implements Screen {

    private final GdxGame game;
    private PerspectiveCamera camera;
    private ModelBatch modelBatch;
    private ModelInstance model;
    private CameraInputController cameraInputController;
    private Environment environment;

    public EditorScreen(GdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1, 1, 1, 1));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        modelBatch = new ModelBatch();

        camera = new PerspectiveCamera(67, WIDTH, HEIGHT);
        camera.position.set(5, 5, 5);
        camera.lookAt(0, 0, 0);
        camera.far = 100;
        camera.near = 1;

        cameraInputController = new CameraInputController(camera);
        cameraInputController.autoUpdate = true;
        Gdx.input.setInputProcessor(cameraInputController);

        ObjLoader loader = new ObjLoader();
//        model = new ModelInstance(loader.loadModel(Gdx.files.internal("Teapot.obj")));
        model = new ModelInstance(new ModelBuilder().createBox(
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

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        cameraInputController.update();

        modelBatch.begin(camera);
        modelBatch.render(model, environment);
        modelBatch.end();
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

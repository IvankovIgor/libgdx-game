package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

public class GdxGame extends Game {

    public static final int HEIGHT = 480;
    public static final int WIDTH = 720;

    public SpriteBatch batch;

    private PerspectiveCamera camera;
    private ModelBatch modelBatch;
    private ModelInstance model;
    private CameraInputController cameraInputController;

    private Environment environment;

    @Override
    public void create() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1, 1, 1, 1));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        batch = new SpriteBatch();
//        setScreen(new MainScreen(this));
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
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        cameraInputController.update();

        modelBatch.begin(camera);
        modelBatch.render(model, environment);
        modelBatch.end();
    }
}

package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class Console {

    private final MessageHandler messageHandler;
    private final Stage stage;
    private final TextArea textArea;
    private final TextField textField;

    public Console(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
        this.stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        this.textArea = new TextArea("", skin);
        this.textArea.setPosition(30, 50);
        this.textArea.setSize(300, 75);
        this.textArea.setPrefRows(5);

        this.textField = new TextField("", skin);
        this.textField.setPosition(30, 30);
        this.textField.setSize(300, 15);
        this.textField.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode== Input.Keys.ENTER){
                    String message = textField.getText();
                    textField.setText("");

                    messageHandler.handle(message);


                    textArea.moveCursorLine(textArea.getCursorLine()+1);
                    textArea.setText(message);
                }
                return super.keyDown(event, keycode);
            }
        });

        this.stage.addActor(textArea);
        this.stage.addActor(textField);
    }

    public InputProcessor getInputProcessor() {
        return stage;
    }

    public void draw() {
        stage.act();
        stage.draw();
    }
}

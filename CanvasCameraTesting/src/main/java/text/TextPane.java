package main.java.text;

import javafx.scene.layout.Pane;

public class TextPane {

    Pane pane;
    public TextHandler textHandler;

    public TextPane(){

         pane = new Pane();
         textHandler = new TextHandler();
         draw();
    }

    public void draw(){

        textHandler.init(pane);

    }

    public void update(){
        textHandler.update();
    }

    public Pane getPane(){
        return pane;
    }

}

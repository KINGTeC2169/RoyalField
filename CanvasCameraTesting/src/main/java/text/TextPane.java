package main.java.text;

import javafx.scene.layout.Pane;

public class TextPane {

    Pane pane;
    TextHandler textHandler;

    public TextPane(){

         pane = new Pane();
         textHandler = new TextHandler();
         draw();
    }

    public void draw(){

        textHandler.init(pane);

    }

    public Pane getPane(){
        return pane;
    }

}

package main.java.UI.text;

import javafx.scene.layout.Pane;

public class TextPane {

    private Pane pane;
    private TextHandler textHandler;

    public TextPane(){

         pane = new Pane();
         textHandler = new TextHandler();
         draw();
    }

    private void draw(){

        textHandler.init(pane);

    }

    public void update(){
        textHandler.update();
    }

    public Pane getPane(){
        return pane;
    }

}

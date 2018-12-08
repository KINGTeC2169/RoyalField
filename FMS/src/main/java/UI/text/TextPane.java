package main.java.UI.text;

import javafx.scene.layout.Pane;
import main.java.UI.text.textHandlers.TextHandler;

public class TextPane {

    private Pane pane;
    private TextHandler textHandler;

    public TextPane(TextHandler textHandler){

         pane = new Pane();
         this.textHandler = textHandler;
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

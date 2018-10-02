package main.java.textHandler;

import javafx.scene.layout.Pane;

public class TextPane {

    Pane pane;

    public TextPane(){

         pane = new Pane();
         draw();

    }

    public void draw(){

        ScoreText t = new ScoreText(1920f/2f, 50);
        t.setText("hi");
        pane.getChildren().add(t);

    }

    public Pane getPane(){
        return pane;
    }

}

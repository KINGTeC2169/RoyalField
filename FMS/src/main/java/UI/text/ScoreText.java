package main.java.UI.text;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

class ScoreText extends Text {

    //Most of this class is pretty self-explanatory.  Holds information about individual pieces of text on the
    //scoreboard.

    private double x_;
    private double y_;

    ScoreText(double x, double y){
        super();
        x_ = x;
        y_ = y;
        this.setX(x);
        this.setY(y);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setFont(Font.font("VCR OSD Mono", 70));
        this.setFill(Color.WHITE);
    }

    ScoreText(double x, double y, int size){
        super();
        x_ = x;
        y_ = y;
        this.setX(x);
        this.setY(y);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setFont(Font.font("VCR OSD Mono", size));
        this.setFill(Color.WHITE);
    }

    void updateText(String s){
        this.setText(s);
        setPos(x_, y_);
    }


    //Take information about the width of the text, and use that to adjust the text to keep it centered on the
    //desired X,Y as opposed to the default, which is the top left corner.  This allows text to appear to stay in place,
    //regardless of how many digits it is or the width of the font, assuming a monospace text hasn't been selected.
    private void setPos(double x, double y){

        this.setX(x - (this.getLayoutBounds().getWidth() * 0.5));
        this.setY(y);
    }

}

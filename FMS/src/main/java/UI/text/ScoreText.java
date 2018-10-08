package main.java.UI.text;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

class ScoreText extends Text {

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

    private void setPos(double x, double y){

        this.setX(x - (this.getLayoutBounds().getWidth() * 0.5));
        this.setY(y);
    }

}

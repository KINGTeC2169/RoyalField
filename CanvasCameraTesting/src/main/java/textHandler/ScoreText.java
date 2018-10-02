package main.java.textHandler;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ScoreText extends Text {

    private enum allianceType{
        RED, BLUE, NEITHER
    }
    public allianceType alliance = allianceType.NEITHER;

    public ScoreText(double x, double y){
        super();
        this.setX(x);
        this.setY(y);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setFont(Font.font("VCR OSD Mono", 70));
        this.setFill(Color.WHITE);
    }

}

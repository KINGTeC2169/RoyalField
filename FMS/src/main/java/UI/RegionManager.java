package main.java.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import main.java.UI.text.TextPane;
import main.java.UI.webcamHandlers.WebCamService;
import main.java.UI.webcamHandlers.WebCamView;

public class RegionManager {


    public StackPane getMatchView(TextPane textPane, WebCamService service){

        BorderPane imagePlacement = new BorderPane();

        //MatchView StackPane
        StackPane matchView = new StackPane();
        Image image = new Image("bin/media/ScoringOverlay.png",1920,250, true, false);
        ImageView iv1 = new ImageView(image);
        matchView.getChildren().add(iv1);
        matchView.getChildren().add(textPane.getPane());
        imagePlacement.setTop(matchView);

        WebCamView view = new WebCamView(service);

        StackPane matchStackPane = new StackPane();
        matchStackPane.getChildren().add(view.getView());
        matchStackPane.getChildren().add(imagePlacement);
        return matchStackPane;
    }


    public StackPane getPostMatchView(TextPane textPane){

        BorderPane imagePlacement = new BorderPane();

        //MatchView StackPane
        StackPane matchView = new StackPane();
        Image image = new Image("bin/media/results.png",1920,1080, true, false);
        ImageView iv1 = new ImageView(image);
        matchView.getChildren().add(iv1);
        matchView.getChildren().add(textPane.getPane());
        imagePlacement.setTop(matchView);

        StackPane matchStackPane = new StackPane();
        matchStackPane.getChildren().add(imagePlacement);
        return matchStackPane;
    }


    public StackPane getRankingsView(TextPane textPane){

        BorderPane imagePlacement = new BorderPane();

        //MatchView StackPane
        StackPane matchView = new StackPane();
        Image image = new Image("bin/media/rankings.png",1920,1080, true, false);
        ImageView iv1 = new ImageView(image);
        matchView.getChildren().add(iv1);
        matchView.getChildren().add(textPane.getPane());
        imagePlacement.setTop(matchView);

        StackPane matchStackPane = new StackPane();
        matchStackPane.getChildren().add(imagePlacement);
        return matchStackPane;
    }

}

package main.java.UI;

import com.github.sarxos.webcam.Webcam;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.Main;
import main.java.UI.text.TextPane;
import main.java.UI.webcamHandlers.WebCamService;
import main.java.UI.webcamHandlers.WebCamView;

public class UIMain extends Application {
	
	private WebCamService service;

	enum UIState{
		PRE, MATCH, POST, INTER
	}
	public UIState state = UIState.PRE;

	@Override
	public void init() {
		
		// note this is in init as it **must not** be called on the FX Application Thread:

		Webcam cam = Webcam.getWebcams().get(0);
		service = new WebCamService(cam);
	}

	@Override
	public void start(Stage primaryStage) {

		TextPane textPane = new TextPane();

		BorderPane imagePlacement = new BorderPane();
		service.restart();

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
			Scene matchScene = new Scene(matchStackPane);

		primaryStage.setScene(matchScene);
		primaryStage.setMaximized(true);
		primaryStage.setFullScreen(true);
		primaryStage.show();

		//F11 for Fullscreen
        matchScene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.F11) {
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });

        Timeline updateMachine = new Timeline(new KeyFrame(Duration.millis(250), event -> textPane.update()));
        updateMachine.setCycleCount(Timeline.INDEFINITE);
        updateMachine.play();

        primaryStage.setOnCloseRequest(we -> {
            primaryStage.close();
            System.out.println("FMS Shutting Down");
            System.exit(Main.shutdown());
        });

	}

	public static void start(String[] args) {
		launch(args);
	}
}

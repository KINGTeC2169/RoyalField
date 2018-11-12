package main.java.UI;

import com.github.sarxos.webcam.Webcam;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

        StackPane imageStack = new StackPane();
		Image image = new Image("bin/media/ScoringOverlay.png",1920,250, true, false);
		ImageView iv1 = new ImageView(image);
		imageStack.getChildren().add(iv1);
        imageStack.getChildren().add(textPane.getPane());
		imagePlacement.setTop(imageStack);

		WebCamView view = new WebCamView(service);

		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(view.getView());
		stackPane.getChildren().add(imagePlacement);

		Scene scene = new Scene(stackPane);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.setFullScreen(true);
		primaryStage.show();

		//F11 for Fullscreen
        scene.setOnKeyPressed(e -> {
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

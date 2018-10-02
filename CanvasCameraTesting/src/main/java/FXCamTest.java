package main.java;

import com.github.sarxos.webcam.Webcam;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.WebCamService;

public class FXCamTest extends Application {
	
	private WebCamService service;
	
	@Override
	public void init() {
		
		// note this is in init as it **must not** be called on the FX Application Thread:

		Webcam cam = Webcam.getWebcams().get(0);
		service = new WebCamService(cam);	
	}

	@Override
	public void start(Stage primaryStage) {

		BorderPane imagePlacement = new BorderPane();
		service.restart();

		Image image = new Image("main/java/ScoringOverlay.jpeg",1920,250, false, false);
		ImageView iv1 = new ImageView(image);
		imagePlacement.setTop(iv1);

		WebCamView view = new WebCamView(service);

		StackPane stackPane = new StackPane();
		stackPane.getChildren().add(view.getView());
		stackPane.getChildren().add(imagePlacement);


		Scene scene = new Scene(stackPane);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}
	


	public static void main(String[] args) {
		launch(args);
	}
}


package main.java;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * This example demonstrates how to use Webcam Capture API via FXML in a JavaFX
 * application.
 * 
 * @author Rakesh Bhatt (rakeshbhatt10)
 */
public class WebCamAppLauncher extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = null;
		root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("main/resources/fxml/WebCamPreview.fxml")));


		Scene scene = new Scene(root, 900, 690);

		primaryStage.setTitle("WebCam Capture Sarxos API using JavaFx with FXML ");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();

	}

	public static void main(String[] args) {
		launch(args);
	}
}

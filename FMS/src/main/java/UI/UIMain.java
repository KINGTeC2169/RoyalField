package main.java.UI;

import com.github.sarxos.webcam.Webcam;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.Main;
import main.java.UI.text.TextPane;
import main.java.UI.text.textHandlers.MatchTextHandler;
import main.java.UI.text.textHandlers.PostTextHandler;
import main.java.UI.webcamHandlers.WebCamService;

public class UIMain extends Application {
	
	private WebCamService service;

	public enum UIState{
		PRE, MATCH, POST, INTER
	}
	private static UIState state = UIState.PRE;
    private static UIState lastState = UIState.PRE;
	public static void setUIState(UIState state){
        UIMain.lastState = UIMain.state;
        UIMain.state = state;
    }
    private static UIState getUIState(){
	    return state;
    }

    @Override
	public void init() {
		
		// note this is in init as it **must not** be called on the FX Application Thread:

		Webcam cam = Webcam.getWebcams().get(0);
		service = new WebCamService(cam);
	}

	@Override
	public void start(Stage primaryStage) {

        service.restart();

	    RegionManager regions = new RegionManager();

        StackPane pane = new StackPane();
        Scene main = new Scene(pane);

        //Match Pane
        TextPane matchText = new TextPane(new MatchTextHandler());
        StackPane matchPane= new StackPane(regions.getMatchView(matchText, service));

        //Post Match Pane
        TextPane postTextPane = new TextPane(new PostTextHandler());
        StackPane postPane = new StackPane(regions.getPostMatchView(postTextPane));

        //Rankings Pane
        TextPane rankingsText = new TextPane(new MatchTextHandler());
        StackPane rankingsPane = new StackPane(regions.getMatchView(matchText, service));

        pane.getChildren().addAll(matchPane);

        primaryStage.setScene(main);
		primaryStage.setMaximized(true);
		primaryStage.setFullScreen(true);
		primaryStage.show();

		//F11 for Fullscreen

        main.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.F11) {
                primaryStage.setFullScreen(true);
            }
        });


        Timeline updateMachine = new Timeline(new KeyFrame(Duration.millis(250), event -> {
//            System.out.println(getLastUIState() + " " + getLastUIState());

            if(UIMain.state != UIMain.lastState ){
                switch(getUIState()){
                    case PRE:
                        break;
                    case MATCH:
                        pane.getChildren().clear();
                        pane.getChildren().removeAll();
                        pane.getChildren().add(matchPane);
                        break;
                    case POST:
                        pane.getChildren().clear();
                        pane.getChildren().removeAll();
                        pane.getChildren().add(postPane);
                        break;
                    case INTER:
                        break;
                }
                //Prevents this from running multiple times
                UIMain.lastState = UIMain.state;
            }
            matchText.update();
            postTextPane.update();
        }));
        updateMachine.setCycleCount(Timeline.INDEFINITE);
        updateMachine.play();

        primaryStage.setOnCloseRequest(we -> {
            primaryStage.close();
            System.out.println("FMS Shutting Down");
            System.exit(Main.shutdown());
        });

	}

	public void start(String[] args) {
		launch(args);
	}
}

package main;

import com.github.sarxos.webcam.Webcam;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import webcam.WebCamService;
import webcam.WebCamView;

public class Controller {

    WebCamService service;
    WebCamView view;
    @FXML BorderPane camera;
    @FXML Region image;

    public Controller(){

    }

    @FXML
    private void initialize(){
        Webcam cam = Webcam.getWebcams().get(0);
        service = new WebCamService(cam);
        service.restart();
        view = new WebCamView(service);
        camera  = new BorderPane(view.getView());
    }



}

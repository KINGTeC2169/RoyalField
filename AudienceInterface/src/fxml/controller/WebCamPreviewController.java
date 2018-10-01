package fxml.controller;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.sarxos.webcam.WebcamPanel;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import com.github.sarxos.webcam.Webcam;

public class WebCamPreviewController implements Initializable {


    //Constants
    int desiredWebcam = 0;

	
	@FXML Button btnStartCamera;
	@FXML Button btnStopCamera;
	@FXML Button btnDisposeCamera;
	@FXML ComboBox<WebCamInfo> cbCameraOptions;
	@FXML BorderPane bpWebCamPaneHolder;
	@FXML FlowPane fpBottomPane;
	@FXML ImageView imgWebCamCapturedImage;
	private BufferedImage grabbedImage;
	private Webcam selWebCam = null;
	private boolean stopCamera = false;
	private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
	
	private String cameraListPromptText = "Choose Camera";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		fpBottomPane.setDisable(true);
		ObservableList<WebCamInfo> options = FXCollections.observableArrayList(
				);
		int webCamCounter = 0;
		for(Webcam webcam:Webcam.getWebcams())
		{
			WebCamInfo webCamInfo = new WebCamInfo();
			webCamInfo.setWebCamIndex(webCamCounter);
			webCamInfo.setWebCamName(webcam.getName());
			options.add(webCamInfo);
			webCamCounter ++;
		}
		cbCameraOptions.setItems(options);
		cbCameraOptions.setPromptText(cameraListPromptText);
        initializeWebCam(desiredWebcam);

		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				
				setImageViewSize();
			}
		});
		
	}
	protected void setImageViewSize() {
		
		double height = bpWebCamPaneHolder.getHeight();
		double width  = bpWebCamPaneHolder.getWidth();
		imgWebCamCapturedImage.setFitHeight(height);
		imgWebCamCapturedImage.setFitWidth(width);
		imgWebCamCapturedImage.prefHeight(height);
		imgWebCamCapturedImage.prefWidth(width);
		imgWebCamCapturedImage.setPreserveRatio(true);
		
	}
	protected void initializeWebCam(final int webCamIndex) {
		
		Task<Void> webCamIntilizer = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				if(selWebCam == null)
				{
					selWebCam = Webcam.getWebcams().get(webCamIndex);
					selWebCam.open();
				}else
				{
					closeCamera();
					selWebCam = Webcam.getWebcams().get(webCamIndex);
					selWebCam.open();

				}
				startWebCamStream();
				return null;
			}
			
		};
		
		new Thread(webCamIntilizer).start();
		fpBottomPane.setDisable(false);
		btnStartCamera.setDisable(true);
	}
	
	protected void startWebCamStream() {
		
		stopCamera  = false;
		Task<Void> task = new Task<Void>() {

		
			@Override
			protected Void call() throws Exception {

				while (!stopCamera) {
					try {
						if ((grabbedImage = selWebCam.getImage()) != null) {
							
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									final Image mainImage
											= SwingFXUtils
											.toFXImage(grabbedImage, null);
									imageProperty.set(mainImage);
								}
							});

							grabbedImage.flush();

						}
					} catch (Exception e) {
					} finally {

					}

				}

				return null;

			}

		};
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		imgWebCamCapturedImage.imageProperty().bind(imageProperty);
		
	}

	
	
	private void closeCamera()
	{
		if(selWebCam != null)
		{
			selWebCam.close();
		}
	}
	public void stopCamera(ActionEvent event)
	{
		stopCamera = true;
		btnStartCamera.setDisable(false);
		btnStopCamera.setDisable(true);
	}
	
	public void startCamera(ActionEvent event)
	{
		stopCamera = false;
		startWebCamStream();
		btnStartCamera.setDisable(true);
		btnStopCamera.setDisable(false);
	}
	
	public void disposeCamera(ActionEvent event)
	{
		stopCamera = true;
		closeCamera();
		Webcam.shutdown();
		btnStopCamera.setDisable(true);
		btnStartCamera.setDisable(true);
	}
	
	class WebCamInfo
	{
		private String webCamName ;
		private int webCamIndex ;

		public void setWebCamName(String webCamName) {
			this.webCamName = webCamName;
		}
		public void setWebCamIndex(int webCamIndex) {
			this.webCamIndex = webCamIndex;
		}
		
		@Override
		public String toString() {
		        return webCamName;
	     }
		
	}
}